class Solution {
    int[] parent;
    int[] size;
    public void init(int n){
        parent = new int[n];
        size = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    public int find(int x){
        if(parent[x]==x) return x;
        return parent[x] = find(parent[x]);
    }
    public boolean union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);

        if(parentA==parentB) return false;

        if(size[parentA]>=size[parentB]){
            parent[parentB] = parentA;
            size[parentA] += size[parentB];
        }else{
            parent[parentA] = parentB;
            size[parentB] += size[parentA];
        }
        return true;
    }
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n+1];
        int[] edge1 = new int[2];
        int[] edge2 = new int[2];
        // store the 2 parent edges if it is the case
        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            if(parent[v]!=0){
                edge1[0] = parent[v];
                edge1[1] = v;
                edge2[0] = u;
                edge2[1] = v;
                break;
            }else{
                parent[v] = u;
            }
        }

        if(edge2[1]!=0){ // 2 parent case
            // remove edge that is causing the cycle
            init(n+1);
            for(int[] e : edges){
                int u = e[0];
                int v = e[1];
                if(u==edge2[0] && v==edge2[1]) continue; // skip edge 2
                if(!union(u, v)){ // and still edge 1 id causing the cycle
                    return edge1; // remove it
                }
            }
            return edge2; // otherwise edge2 is causing the cycle
        }

        // else
        // means cycle exist problem case
        init(n+1);
        int[] res = new int[2];
        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            if(!union(u, v)){
                // remove the edge that is causing the cycle which is lastmost cycle causing edge in input
                res[0] = u;
                res[1] = v;
            }
        }
        return res;
    }
}