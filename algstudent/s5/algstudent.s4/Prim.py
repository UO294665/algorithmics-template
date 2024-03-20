import Helper as h

def Prim_Function(m):
    pivots = [0]
    nodesFrom = []
    costs = []
    edges = [False] * len(m)
    edges[0] = True
    results = 0
    nodeTo=1
    nodeFrom=0
    while(len(pivots)!=len(m)):
        aux = 10000
        for i in pivots:
            for j in range(len(m[i])):
                if(m[i][j] < aux and not edges[j] and m[i][j]!=0):
                    aux = m[i][j]
                    nodeTo=j
                    nodeFrom=i
            for j in range(len(m[i])):
                if(m[j][i] < aux and not edges[j] and m[j][i]!=0):
                    aux = m[j][i]
                    nodeTo=j
                    nodeFrom=i
        edges[nodeTo] = True
        results+=aux
        pivots.append(nodeTo)
        nodesFrom.append(nodeFrom)
        costs.append(aux)
    return results, pivots, nodesFrom, costs


m=h.triangularMatrixFromFile("graph16.txt")
results, pivots, nodesFrom, costs = Prim_Function(m)

print(results)
for i in range(len(pivots)-1):
    print("Arist: ", i+1, "Node from: ", nodesFrom[i], " to node ", pivots[i+1], " with cost ", costs[i])
aux = 1