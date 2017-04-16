package com.marinshalamanov.hashcode17;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Hashcode17 {
	
	Random r = new Random();
	
	int numVideos, numEndpoints, numReq, numCacheServers, capacity;
	
	int videoSizes[];
	
	Endpoint endpoints[];
	Req requests[];
	
	public void readInput(InputReader in) {
		numVideos = in.nextInt();
		numEndpoints = in.nextInt();
		numReq = in.nextInt();
		numCacheServers = in.nextInt();
		capacity = in.nextInt();
		
		videoSizes = new int[numVideos];
		for(int i = 0; i < numVideos; i++) {
			videoSizes[i] = in.nextInt();
		}
		
		endpoints = new Endpoint[numEndpoints];
		requests = new Req[numReq];
		
		for(int i = 0; i < numEndpoints; i++) {
			endpoints[i] = new Endpoint();
			endpoints[i].LD = in.nextInt();
			endpoints[i].numCache = in.nextInt();
			int k = endpoints[i].numCache;
			endpoints[i].serverIds = new int[k];
			endpoints[i].cacheLat = new int[k];
					
			for(int j = 0; j < k; j++) {
				endpoints[i].serverIds[j] = in.nextInt();
				endpoints[i].cacheLat[j] = in.nextInt();
			}
		}
		
		for(int i = 0; i < numReq; i++) {
			requests[i] = new Req();
			requests[i].videoId = in.nextInt();
			requests[i].endpointId = in.nextInt();
			requests[i].numReqs = in.nextInt();
		}
	}
	
	
	ArrayList<Solution> population = new ArrayList<>();
	final int POP_SIZE = 2;
	
	void addSolToPop(Solution s) {
		population.add(s);
		
		Collections.sort(population);
		for(int i = population.size()-1; i > POP_SIZE; i--) population.remove(i);
	}


	final int INTERATIONS = 1;
    
	
	public void solve(InputReader in, PrintWriter out) {
		
		
		
		solveFile(in, out);
	}
	
	public void solveFile(InputReader in, PrintWriter out) {
		readInput(in);
		
		randVideoIds.clear();
		for(int k = 0 ; k < numVideos; k++) {
			randVideoIds.add(k);
		}
		

        long bestTime  = -1;;
        Solution bestSol = null;
        
        for(int i = 0; i < POP_SIZE; i++) {
        	if(i%10000 == 0)
        		System.out.println("init " + i + " : " + bestTime);
	        Solution randomSolution = new Solution();
	        
	        randomSolution.fromFile();
	        
	      
	        
	        hillClimb(randomSolution);
	        
	        long time = randomSolution.getTime();
			
			if(time > bestTime) {
				bestTime = time;	
				bestSol = randomSolution;
			}
			
			population.add(randomSolution);
        }
        
        
        System.out.println(bestTime);
        bestSol.output(out);
    }
	
	public void solveHC(InputReader in, PrintWriter out) {
        readInput(in);

        long bestTime  = -1;;
        Solution bestSol = null;
        
        for(int i = 0; i < 10000; i++) {
        	if(i%100 == 0)
        		System.out.println("iteration " + i + " : " + bestTime);
	        
        	Solution randomSolution = randomSolution();
			hillClimb(randomSolution);
			
        	long time = randomSolution.getTime();
			
			if(time > bestTime) {
				bestTime = time;
				bestSol = randomSolution;
			}
        }
        
        System.out.println(bestTime);
        bestSol.output(out);
    }
	
	public void solveGen(InputReader in, PrintWriter out) {
        readInput(in);

        long bestTime  = -1;;
        Solution bestSol = null;
        
        for(int i = 0; i < POP_SIZE; i++) {
        	if(i%10000 == 0)
        		System.out.println("init " + i + " : " + bestTime);
	        Solution randomSolution = randomSolution();
	        
	        hillClimb(randomSolution);
	        
	        long time = randomSolution.getTime();
			
			if(time > bestTime) {
				bestTime = time;
				bestSol = randomSolution;
			}
			
			population.add(randomSolution);
        }
        
        Collections.sort(population);
        
        for(int i = 0; i < INTERATIONS; i++) {
        	if(i > 2000) {
//        		if(r.nextBoolean()) {
        			addSolToPop(randomSolution());
//        		}
        	}
        	crossover(select(), select());
        	
        	bestSol = population.get(0);
        	bestTime = bestSol.getTime();
        	
        	if(i%100 == 0)
        		System.out.println("iteration: " + i + " best time: " + bestTime);
        }
        
        System.out.println(bestTime);
        bestSol.output(out);
    }
	
	void mutate(Solution s) {
//		if(r.nextBoolean()) { // with 50% change
			// rebuild one of the servers
			
			int serverId = r.nextInt(numCacheServers);
			rebuildRandomServer(s, serverId);
//		}
	}

	Solution select() {
		int id = r.nextInt(POP_SIZE);
		return population.get(id);
	}
	
	void rebuildRandomServer(Solution s, int serverId) {
		int currSize = 0;
		
		Collections.shuffle(randVideoIds);
		
		s.serverDescs[serverId].clear();
		
		for(int k = 0 ; k < numVideos; k++) {
			int currVidId = randVideoIds.get(k);
			
			if(currSize + videoSizes[currVidId] <= capacity) {
				s.serverDescs[serverId].add(currVidId);
				currSize += videoSizes[currVidId];
			}
		}
		
		s.serverSizes[serverId] = currSize;
	}
	
	void crossover(Solution s1, Solution s2) {
		Solution c1, c2;
		c1 = new Solution();
		c2 = new Solution();
		
		for(int i = 0; i < numCacheServers; i++) {
			if(r.nextBoolean()) {
				c1.serverDescs[i] = s1.serverDescs[i];
				c2.serverDescs[i] = s2.serverDescs[i];
				
				c1.serverSizes[i] = s1.serverSizes[i];
				c2.serverSizes[i] = s2.serverSizes[i];
			} else {
				c1.serverDescs[i] = s2.serverDescs[i];
				c2.serverDescs[i] = s1.serverDescs[i];
				
				c1.serverSizes[i] = s2.serverSizes[i];
				c2.serverSizes[i] = s1.serverSizes[i];
			}
		}
		
		for (int i = 0; i < 5; i++) {
			mutate(c1);
			mutate(c2);
		}
		
		hillClimb(c1);
		hillClimb(c2);
		
		c1.getTime(true);
		c2.getTime(true);
		
		addSolToPop(c1);
		addSolToPop(c2);
	}
	
	
	class Endpoint {
		public int LD, numCache;
		public int serverIds[], cacheLat[];
	}
	
	class Req {
		int videoId, endpointId, numReqs;
	}
	
	class Solution implements Comparable<Solution> {
		List<Integer> serverDescs [] = new List[numCacheServers];
		int serverSizes[] = new int[numCacheServers];
		long serverBenefits[] = new long[numCacheServers];
		
		public Solution() {
			for(int i = 0; i < serverDescs.length; i++) {
				serverDescs[i] = new ArrayList<Integer>();
			}
		}
		
		public void output(PrintWriter out) {
			out.println(numCacheServers);
			for(int i = 0; i < numCacheServers; i++) {
				out.print(i + " ");
				for(int vidId : serverDescs[i]) {
					out.print(vidId + " ");
				}
				out.println();
			}
		}
		
		void fromFile() {
			File f = new File("me_at_the_zoo_best.out");
			
			try {
				Scanner in =  new Scanner(f);
		    	
		    	int n = in.nextInt();
		    	in.nextLine();
		    	for(int i = 0; i < n; i++) {
		    		Scanner line = new Scanner(in.nextLine());
		    		int id = line.nextInt();
		    		
		    		while(line.hasNextInt()) {
		    			serverDescs[id].add(line.nextInt());
		    		}
		    	}
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    	
	    	
		}
		
		public Solution clone() {
			Solution s = new Solution();
			for(int i = 0; i < numCacheServers; i++) {
				s.serverDescs[i].addAll(serverDescs[i]);
				s.serverSizes[i] = serverSizes[i];
				s.serverBenefits[i] = serverBenefits[i];
			}
			return s;
		}
		
		public boolean check() {
			// assumes server sizes are correct
			
			for(int i = 0; i < numCacheServers; i++) {
				if(serverSizes[numCacheServers] >= capacity) {
					return false;
				}
			}
			return true;
			
//			for(List<Integer> ser : serverDescs) {
//				int totalMG = 0;
//				for(Integer v : ser) {
//					totalMG += videoSizes[v];
//				}
//				
//				if(totalMG > capacity) return false;
//				
//			}			
//			return true;
		}
		
		long time = -1;
		
		public long getTime() {
			return getTime(false);
		}
		public long getTime(boolean rebuild) {
			if(time == -1 || rebuild)  {
				
				long totalBenefit = 0;
				for(Req r : requests) {
					Endpoint endpoint = endpoints[r.endpointId];
					int videoId = r.videoId;
					
					int minLat = Integer.MAX_VALUE;
					
					int closestServerId = 0;
					
					for(int i = 0 ; i < endpoint.serverIds.length; i++) {
						int serverid = endpoint.serverIds[i];
						if(serverDescs[serverid].contains(videoId)) {
							if(endpoint.cacheLat[i] < minLat) {
								minLat = endpoint.cacheLat[i];
								closestServerId = serverid;
							}
//							minLat = Math.min(minLat, endpoint.cacheLat[i]);
						}
					}
					if(minLat == Integer.MAX_VALUE) minLat = endpoint.LD;
					
					long benefit = (endpoint.LD - minLat)*r.numReqs;
					totalBenefit += benefit;
					
					serverBenefits[closestServerId] += benefit;
				}
				
				time = totalBenefit;
			}
//			System.out.println(time);
			return time;
		}

		@Override
		public int compareTo(Solution o) {
			return Long.compare(o.getTime(), this.getTime());
		}
	}
	
	ArrayList<Integer> randVideoIds = new ArrayList<>();
	
	Solution randomSolution () {
		Solution s  = new Solution();
		
		randVideoIds.clear();
		for(int k = 0 ; k < numVideos; k++) {
			randVideoIds.add(k);
		}
		
		//int intent[][] = new int[numVideos][numCacheServers];
		for(int i = 0 ; i < numCacheServers; i++) {
			int currSize = 0;
			
			Collections.shuffle(randVideoIds);
			
			for(int k = 0 ; k < numVideos; k++) {
				int currVidId = randVideoIds.get(k);
				
				if(currSize + videoSizes[currVidId] <= capacity) {
					s.serverDescs[i].add(currVidId);
					currSize += videoSizes[currVidId];
				}
			}
			
			s.serverSizes[i] = currSize;
		}
		
		return s;
	}
	
	void hillClimb(Solution s) {
		final int HC_STEPS = 100000;
		
		for(int step = 0; step < HC_STEPS; step++ ) {
			long time = s.getTime();
			if(step % 100 == 0)
				System.out.println("hill climb step " + step + "; time is " + time );
			
			long minBenefit = Long.MAX_VALUE;
			int minBenefitServerId = -1;
			
			for(int i = 0; i < numCacheServers; i++) {
				if(s.serverBenefits[i] < minBenefit) {
					minBenefit = s.serverBenefits[i];
					minBenefitServerId = i;
				}
			}
//			minBenefitServerId = r.nextInt(numCacheServers);
			//System.out.println(minBenefit + " " + minBenefitServerId);
			
			Solution old = s.clone();
			rebuildRandomServer(s, minBenefitServerId);
			long newTime = s.getTime(true);
			if(time > newTime) {
				s = old;
			}
		}
	}
	
	
    public static void main(String[] args) throws FileNotFoundException {
    	
    	String tests[] = { "videos_worth_spreading","trending_today","kittens","me_at_the_zoo"};
    	int testid = 3;
    	
    	File inf = new File(tests[testid] + ".in");
    	InputStream inputStream = new FileInputStream(inf);
    	
    	File f = new File(tests[testid] + ".out");
        PrintWriter out = new PrintWriter(f);
        
//        InputStream inputStream = System.in;
//        OutputStream outputStream = System.out;
//        
        
        InputReader in = new InputReader(inputStream);
//        PrintWriter out = new PrintWriter(outputStream);
        
        Hashcode17 solver = new Hashcode17();
        solver.solve(in, out);
        out.close();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
