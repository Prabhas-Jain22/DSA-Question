class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> graph []=new List[numCourses];
        for(int i = 0 ; i < numCourses ; i++){
            graph[i]= new ArrayList<>();
        }
        int[] inDeg = new int[numCourses];
        for(int[] pre :prerequisites){
            graph[pre[1]].add(pre[0]);
            inDeg[pre[0]]++;
        }  
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0 ; i < inDeg.length;i++){
            if(inDeg[i]==0){
                pq.add(i);
            }
        }
        int cnt = 0 ;
        int ans[]=new int[numCourses];
        int ind=0; 
        while(!pq.isEmpty()){
            int k = pq.poll();
            ans[ind]=k;
            ind++;
            cnt++;
            for(int nei : graph[k]){
                inDeg[nei]--;
                if(inDeg[nei]==0){
                    pq.add(nei);
                }
            }
        }
        if(cnt==numCourses) return ans;
        return new int[]{};
    }
}