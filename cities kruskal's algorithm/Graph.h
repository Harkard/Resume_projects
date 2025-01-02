// Include necessary header files here
#include "Edge.h" // Assuming City class is defined in City.h

using namespace std;

namespace graph
{

    class Graph
    {
    private:
        int V, E;      // number of vertices and edges in the graph
        City *cityArr; // an array of City objects
        Edge *edgeArr; // an array of Edge objects

    public:
        Graph(int numOfCities, int numOfEdges, City *cityArray, Edge *edgeArray);
        ~Graph(); // Destructor
        void make_set(City *aCity);
        City *find_set(City *aCity);
        void link(City *aCity, City *bCity);
        void Union(City *aCity, City *bCity);
        void sortEdges();

        void MST_Kruskal();

        // add any auxiliary functions in case you need them
        void MergeSortEdges();
    };

}

using namespace graph;

Graph::Graph(int numOfCities, int numOfEdges, City *cityArray, Edge *edgeArray)
{
    V = numOfCities;
    E = numOfEdges;
    cityArr = cityArray;
    edgeArr = edgeArray;
}

Graph::~Graph()
{
    delete[] cityArr;
    delete[] edgeArr;
}

void Graph::make_set(City *aCity)
{
    aCity->parent = aCity;
    aCity->rank = 0;
}

City *Graph::find_set(City *aCity)
{
    if (aCity != aCity->parent)
        aCity->parent = find_set(aCity->parent);
    return aCity->parent;
}

void Graph::link(City *aCity, City *bCity)
{
    if (aCity->rank > bCity->rank)
        bCity->parent = aCity;
    else
    {
        aCity->parent = bCity;
        if (aCity->rank == bCity->rank)
            bCity->rank++;
    }
}

void Graph::Union(City *aCity, City *bCity)
{
    link(find_set(aCity), find_set(bCity));
}

void merge(Edge *arr, int left, int mid, int right)
{
    int n1 = mid - left + 1;
    int n2 = right - mid;

    Edge leftArr[n1], rightArr[n2];

    for (int i = 0; i < n1; i++)
        leftArr[i] = arr[left + i];
    for (int j = 0; j < n2; j++)
        rightArr[j] = arr[mid + 1 + j];

    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2)
    {
        if (leftArr[i].getDistance() <= rightArr[j].getDistance())
        {
            arr[k] = leftArr[i];
            i++;
        }
        else
        {
            arr[k] = rightArr[j];
            j++;
        }
        k++;
    }

    while (i < n1)
    {
        arr[k] = leftArr[i];
        i++;
        k++;
    }

    while (j < n2)
    {
        arr[k] = rightArr[j];
        j++;
        k++;
    }
}

void mergeSort(Edge *arr, int left, int right)
{
    if (left < right)
    {
        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }
}

void Graph::sortEdges()
{
    mergeSort(edgeArr, 0, E - 1);
}

void Graph::MergeSortEdges()
{
    mergeSort(edgeArr, 0, E - 1);
}

// MST_Kruskal function to find Minimum Spanning Tree using Kruskal's algorithm
void Graph::MST_Kruskal()
{
    cout << "MST Kruskal Algorithm Result\n"
         << endl;
    double totalWeight = 0.0;

    // Initialize each city as a separate set
    for (int i = 0; i < E; i++)
    {
        // make_set(&cityArr[i]);
        make_set(edgeArr[i].getCity1());
        make_set(edgeArr[i].getCity2());
    }

    // Sort edges in non-decreasing order of weight
    sortEdges();

    // Iterate over sorted edges
    for (int i = 0; i < E; ++i)
    {
        // Find the sets of the cities connected by the edge
        City *city1Set = find_set(edgeArr[i].getCity1());
        City *city2Set = find_set(edgeArr[i].getCity2());

        // If the cities belong to different sets (i.e., not creating a cycle)
        if (city1Set != city2Set)
        {
            // Merge the sets and add the edge to the MST
            Union(city1Set, city2Set);
            totalWeight += edgeArr[i].getDistance();
            // Assuming Edge class has a print function
            edgeArr[i].printEdge();
        }
    }

    cout << "=================================================" << endl;
    cout << "Total Distance: " << totalWeight << endl;
}
