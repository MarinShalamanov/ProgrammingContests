package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Timus1471 {
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		new Timus1471().solve();
	}

	class Node {
		public int node;
		public int weight;

		public Node(int n, int w) {
			node = n;
			weight = w;
		}
	}

	Map<Integer, List<Node>> E = new HashMap<Integer, List<Node>>();

	int[] height;
	int[] dist;
	int[][] p;

	int n;

	void buildP() {
		dist = new int[n];

		dist[0] = 0;
		p[0][0] = 0;

		int[] queue = new int[n + 1];
		int end = 0;
		queue[end++] = 0;

		for (int i = 0; i < end; i++) {
			List<Node> nei = E.get(queue[i]);
			if (nei == null)
				continue;

			for (Node n : nei) {
				if (n.node != p[queue[i]][0]) {
					dist[n.node] = dist[queue[i]] + n.weight;
					height[n.node] = height[queue[i]] + 1;
					queue[end++] = n.node;
					p[n.node][0] = queue[i];
				}
			}
		}

		final int MAXK = 2 + (int) (Math.log(n) / Math.log(2)); // = logN
		for (int k = 1; k < MAXK; k++) {
			for (int i = 0; i < n; i++) {
					p[i][k] = p[p[i][k - 1]][k - 1];
			}
		}
		
	}

	int lca(int u, int v) {
		if (height[v] > height[u]) {
			int tmp = v;
			v = u;
			u = tmp;
		}

		while (height[u] > height[v]) {
			int delta = height[u] - height[v];

			int k;
			for (k = 0; (1 << k) < delta; k++)
				;
			if(k>0)k--;

			u = p[u][k];
		}
		//System.out.println(u + " " + v);

		while (u != v) {
			int k;
			for (k = 0; p[u][k] != p[v][k]; k++)
				;
			if (k > 0)
				k--;

			u = p[u][k];
			v = p[v][k];
		}

		return u;
	}

	int get(int u, int v) {
		int lca = lca(u, v);
		//System.out.println(u + " " + v + " " + lca);
		
		return dist[u] + dist[v] - 2 * dist[lca];
	}

	void solve() throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(in.readLine());

		height = new int[n];
		p = new int[n][2 + (int) (Math.log(n) / Math.log(2))];

		int u, v, w;
		for (int i = 1; i < n; i++) {
			String line = in.readLine();
			StringTokenizer st = new StringTokenizer(line);

			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			if (!E.containsKey(u)) {
				E.put(u, new ArrayList<Node>());
			}

			if (!E.containsKey(v)) {
				E.put(v, new ArrayList<Node>());
			}

			E.get(u).add(new Node(v, w));
			E.get(v).add(new Node(u, w));
		}

		buildP();

		int m = Integer.parseInt(in.readLine());
		for (int i = 0; i < m; i++) {
			String line = in.readLine();
			StringTokenizer st = new StringTokenizer(line);

			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			out.println(get(u, v));
		}

		out.flush();
	}
}
