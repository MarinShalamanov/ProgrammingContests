package com.marinshalamanov.codeforces.codeforcesRocketon2015;

import java.util.Scanner;

public class C {
    
    public static void main(String[] args) {
    	
        Scanner sinput = new Scanner(System.in);
        int N = sinput.nextInt();
        int[] Left = new int[N];
        int[] Right = new int[N];
        for (int i = 0; i < N; i++) {
            Left[i] = sinput.nextInt();
            Right[i] = sinput.nextInt();
        }
        double[] probabs = new double[10001];
        for (int i = 0; i < N; i++) {
            for (int j = Left[i]; j <= Right[i]; j++) {
                double prob = 1./(Right[i]-Left[i]+1);
                double probRes = 0;
                out:
                for (int k = 0; k < N; k++) {
                    if (i == k) continue;
                    
                    double probabMax = (1.*Right[k] - j)/(Right[k]-Left[k]+1);
                    if (probabMax < 0) continue;
                    if (i < k && Left[k]<=j && Right[k] >= j)  probabMax += 1./(Right[k]-Left[k]+1);
                    if (probabMax > 1) probabMax = 1;
                    
                    for (int m = 0; m < N; m++) {
                        if (m==i || m == k) continue;
                        double probabMin = (1.*j - Left[m])/(Right[m]-Left[m]+1);
                        if (probabMin < 0) continue out; 
                        if (m < i && Left[m]<=j && Right[m] >= j)  probabMin += 1./(Right[m]-Left[m]+1);
                        if (probabMin > 1) probabMin = 1;
                        probabMax *= probabMin;
                    }
                    probRes += probabMax;
                }
                prob *= probRes;
                probabs[j] += prob;
            }
        }
        
        double sum = 0;
        double prob = 0;
        for (int i = 1; i < 10001; i++) {
            sum += i *probabs[i];
            prob += probabs[i];
        }
        System.out.println(String.format("%.9f", sum));
    }
}