from collections import defaultdict, deque

class Graph:
    def __init__(self):
        # Inicjalizacja pustego grafu w reprezentacji listy sąsiedztwa
        self.graph = defaultdict(list)

    def add_edge(self, u, v):
    def add_edge(self, u, v):
        # Dodawanie krawędzi między wierzchołkami u i v
        self.graph[u].append(v)
        self.graph[v].append(u)  # Dla grafu nieskierowanego, dodajemy krawędź w obie strony

    def bfs(self, start):
        # Inicjalizacja zbioru odwiedzonych wierzchołków oraz kolejki
        visited = set()
        queue = deque([start])
        visited.add(start)

        # Rozpoczęcie algorytmu BFS
        print("Rozpoczęcie algorytmu BFS od wierzchołka startowego", start)
        while queue:
            current_node = queue.popleft()
            print(current_node, end=' ')

            for neighbor in self.graph[current_node]:
                if neighbor not in visited:
                    queue.append(neighbor)
                    visited.add(neighbor)

    def dfs(self, current_node, visited):
        # Oznacz bieżący wierzchołek jako odwiedzony i wyświetl go
        visited.add(current_node)
        print(current_node, end=' ')

        # Rekurencyjne przetwarzanie nieodwiedzonych sąsiadów
        for neighbor in self.graph[current_node]:
            if neighbor not in visited:
                self.dfs(neighbor, visited)

    def dfs_traversal(self, start):
        # Inicjalizacja zbioru odwiedzonych wierzchołków
        visited = set()

        # Rozpoczęcie algorytmu DFS od wierzchołka startowego
        print("Rozpoczęcie algorytmu DFS od wierzchołka startowego", start)
        self.dfs(start, visited)

# Przykładowe użycie dla różnych grafów
g1 = Graph()
g1.add_edge(1, 2)
g1.add_edge(1, 3)
g1.add_edge(2, 4)
g1.add_edge(2, 5)
g1.add_edge(3, 6)
g1.add_edge(3, 7)
g1.add_edge(4, 8)
g1.add_edge(4, 9)
g1.add_edge(5, 10)
g1.add_edge(6, 11)
g1.add_edge(6, 12)
g1.add_edge(7, 13)
g1.add_edge(7, 14)
g1.add_edge(8, 15)

g2 = Graph()
g2.add_edge(1, 2)
g2.add_edge(1, 3)
g2.add_edge(1, 4)
g2.add_edge(2, 5)
g2.add_edge(2, 6)
g2.add_edge(3, 7)
g2.add_edge(3, 8)
g2.add_edge(4, 9)
g2.add_edge(4, 10)
g2.add_edge(5, 11)
g2.add_edge(5, 12)
g2.add_edge(6, 13)
g2.add_edge(6, 14)
g2.add_edge(7, 15)
g2.add_edge(7, 16)
g2.add_edge(8, 17)

# Wybierz, czy chcesz przeprowadzić BFS czy DFS
search_type = input("Wybierz typ (BFS Lub DFS): ").upper()

start_node = 1  # Wybierz dowolny wierzchołek jako wierzchołek początkowy

if search_type == "BFS":
    g1.bfs(start_node)
    g2.bfs(start_node)
elif search_type == "DFS":
    g1.dfs_traversal(start_node)
    g2.dfs_traversal(start_node)
else:
    print("źle wybrany typ. Proszę wybierz  'BFS' lub 'DFS'.")
