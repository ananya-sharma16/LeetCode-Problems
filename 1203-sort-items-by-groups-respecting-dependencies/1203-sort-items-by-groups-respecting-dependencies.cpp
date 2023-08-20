class Solution {

    vector<int> topoSort(vector<vector<int>> &adj, vector<int> &indegree)
    {
        vector<int> ans;
        queue<int> q;
        for(int i=0;i<indegree.size();i++)
        {
            if(indegree[i]==0)
            q.push(i);
        }

        while(!q.empty())
        {
            int tmp = q.front();
            q.pop();
            ans.push_back(tmp);

            for(auto it:adj[tmp])
            {
                indegree[it]--;
                if(indegree[it]==0)
                q.push(it);
            }
        }

        return ans;
    }
public:
    vector<int> sortItems(int n, int m, vector<int>& group, vector<vector<int>>& beforeItems) {

        for(int i=0;i<n;i++)
        {
            if(group[i]==-1)
            {
                group[i]=m++;
            }
        }
        //items, no of groups, 
        vector<vector<int>> itemGraph(n);
        vector<int> itemIndegree(n);
        vector<vector<int>> groupGraph(m);
        vector<int> groupIndegree(m);

        for(int i=0;i<n;i++)
        {
            for(auto prev: beforeItems[i])
            {
                itemGraph[prev].push_back(i);
                itemIndegree[i]++;

                if(group[i]!=group[prev])
                {
                    int prevItemGroup = group[prev];
                    int currItemGroup = group[i];

                    groupGraph[prevItemGroup].push_back(currItemGroup);
                    groupIndegree[currItemGroup]++;
                }
            }
        }


        vector<int> itemOrder = topoSort(itemGraph, itemIndegree);
        vector<int> groupOrder = topoSort(groupGraph, groupIndegree);

        if(itemOrder.size()!=n or groupOrder.size()!=m) return {};
        vector<vector<int>> groupToItem(m);

        for(auto it: itemOrder)
        {
            int grp = group[it];
            groupToItem[grp].push_back(it);
        }

        vector<int> sortedAns;
        for(int i=0;i<groupOrder.size();i++)
        {
            for(auto it:groupToItem[groupOrder[i]])
            {
                sortedAns.push_back(it);
            }
        }

        return sortedAns;
    }
};