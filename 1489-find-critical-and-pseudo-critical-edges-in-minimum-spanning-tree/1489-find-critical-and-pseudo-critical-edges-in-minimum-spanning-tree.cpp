class UnionFind {
public:
    vector<int> parent;
    vector<int> rank;
    UnionFind(int n) {
        for(int i = 0; i < n; i++) {     
            parent.push_back(i); 
            rank.push_back(1);
        } 
    }

    int find(int v1) {
        while (v1 != parent[v1]) {
            parent[v1] = parent[parent[v1]]; 
            v1 = parent[v1];
        }
        return v1;
    }

    bool uni(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) return false; 
        if (rank[p1] > rank[p2]) {
            parent[p2] = p1;
            rank[p1] += rank[p2];
        } else {
            parent[p1] = p2;
            rank[p2] += rank[p1];
        }
        
        return true; // return true since we could union these 2 sets
    }
};

class Solution {
public:
    vector<vector<int>> findCriticalAndPseudoCriticalEdges(int n, vector<vector<int>>& edges) {
        // [v1, v2, weight, i]
        for (int i = 0; i < edges.size(); i++) {
            edges[i].push_back(i);
        }
        
        // sort the edges by weight in ascending order
        sort(edges.begin(), edges.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[2] < b[2];
        });

        int mst_weight = 0;
        
        UnionFind uf(n); // create a UnionFind instance to manage the connected components

        // construct the MST using Kruskal's algorithm
        for (auto edge : edges) {
            int n1 = edge[0], n2 = edge[1], w = edge[2], i = edge[3];
            if (uf.uni(n1, n2)) {
                mst_weight += w;
            }
        }

        vector<int> critical;
        vector<int> pseudo;

        // iterate through the edges again to find critical and pseudo-critical edges
        for (auto edge : edges) {
            int n1 = edge[0], n2 = edge[1], e_weight = edge[2], i = edge[3];

            int weight = 0;
            UnionFind uf_wout(n); // create a new UnionFind instance to simulate MST without the current edge
            for (auto e : edges) {
                int v1 = e[0], v2 = e[1], w = e[2], j = e[3];

                if (i != j && uf_wout.uni(v1, v2)) {
                    weight += w;
                }
            }
            if (*max_element(uf_wout.rank.begin(), uf_wout.rank.end()) != n || weight > mst_weight) {
                critical.push_back(i); // its a critical edge
                continue; // move to next
            }

            UnionFind uf_with(n); 
            uf_with.uni(n1, n2);
            weight = e_weight;

            // simulate with
            for (auto e : edges) {
                int v1 = e[0], v2 = e[1], w = e[2], j = e[3];
                
                if (i != j && uf_with.uni(v1, v2)) {
                    weight += w;
                }
            }
            if (weight == mst_weight) {
                pseudo.push_back(i);
            }
        }

        return {critical, pseudo};
    }
};
