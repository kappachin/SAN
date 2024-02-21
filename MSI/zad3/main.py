import numpy as np

class AntColonyOptimizer:
    def __init__(self, distances, n_ants, n_best, n_iterations, decay, alpha=1, beta=1):
        """
        Inicjalizacja optymalizatora kolonii mrówek
        :param distances: Macierz odległości między miastami.
        :param n_ants: Liczba mrówek w symulacji.
        :param n_best: Liczba najlepszych mrówek, które wpływają na aktualizację ścieżek feromonowych.
        :param n_iterations: Liczba iteracji algorytmu.
        :param decay: Współczynnik parowania feromonu.
        :param alpha: Współczynnik wpływu śladu feromonowego.
        :param beta: Współczynnik wpływu widoczności (odwrotność odległości).
        """
        self.distances  = distances
        self.pheromone = np.ones(self.distances.shape) / len(distances)
        self.all_inds = range(len(distances))
        self.n_ants = n_ants
        self.n_best = n_best
        self.n_iterations = n_iterations
        self.decay = decay
        self.alpha = alpha
        self.beta = beta

    def display_paths(self, all_paths):
        """
        Wyświetlanie ścieżek i ich długości.
        """
        for path, dist in all_paths:
            print("Ścieżka: ", end='')
            for move in path:
                print(f"{move[0]} -> {move[1]} ", end='')
            print(f"| Długość ścieżki: {dist}")
    def run(self):
        """
        Główna funkcja uruchamiająca optymalizację.
        """
        shortest_path = None
        all_time_shortest_path = ("", np.inf)
        for i in range(self.n_iterations):
            all_paths = self.gen_all_paths()
            self.spread_pheronome(all_paths, self.n_best, shortest_path=shortest_path)
            shortest_path = min(all_paths, key=lambda x: x[1])
            if shortest_path[1] < all_time_shortest_path[1]:
                all_time_shortest_path = shortest_path
            self.pheromone * self.decay
        return all_time_shortest_path

    def spread_pheronome(self, all_paths, n_best, shortest_path):
        """
        Rozprzestrzenianie feromonów na ścieżkach.
        """
        sorted_paths = sorted(all_paths, key=lambda x: x[1])
        for path, dist in sorted_paths[:n_best]:
            for move in path:
                self.pheromone[move] += 1.0 / self.distances[move]

    def gen_path_dist(self, path):
        """
        Obliczanie długości ścieżki.
        """
        total_dist = 0
        for ele in path:
            total_dist += self.distances[ele]
        return total_dist

    def gen_all_paths(self):
        """
        Generowanie wszystkich ścieżek dla mrówek.
        """
        all_paths = []
        for i in range(self.n_ants):
            path = self.gen_path(0)
            all_paths.append((path, self.gen_path_dist(path)))
        return all_paths

    def gen_path(self, start):
        """
        Generowanie pojedynczej ścieżki przez jedną mrówkę.
        """
        path = []
        visited = set()
        visited.add(start)
        prev = start
        for i in range(len(self.distances) - 1):
            move = self.pick_move(self.pheromone[prev], self.distances[prev], visited)
            path.append((prev, move))
            prev = move
            visited.add(move)
        path.append((prev, start)) # powrót do punktu startowego
        return path

    def pick_move(self, pheromone, dist, visited):
        """
        Wybór kolejnego ruchu przez mrówkę.
        """
        pheromone = np.copy(pheromone)
        pheromone[list(visited)] = 0

        row = pheromone ** self.alpha * (( 1.0 / dist) ** self.beta)

        norm_row = row / row.sum()
        move = np_choice(self.all_inds, 1, p=norm_row)[0]
        return move

def np_choice(a, size, replace=True, p=None):
    """
    Wybór elementu z listy zgodnie z rozkładem prawdopodobieństwa.
    """
    return np.array(np.random.choice(a, size=size, replace=replace, p=p))

# Przykładowa macierz odległości
distances = np.array([
    [np.inf, 16,  1,  4,  4,  8],
    [10, np.inf,  5,  7, 13,  2],
    [ 7,  8, np.inf, 18,  6, 14],
    [ 9, 10, 17, np.inf, 16, 16],
    [ 1, 19,  4, 18, np.inf,  8],
    [ 1,  2, 10,  1, 11, np.inf]])

# Inicjalizacja i uruchomienie optymalizatora
aco = AntColonyOptimizer(distances, 10, 3, 100, 0.95, alpha=1, beta=2)
shortest_path = aco.run()
print(shortest_path)

# Przykładowe wywołanie
aco = AntColonyOptimizer(distances, 10, 3, 100, 0.95, alpha=1, beta=2)
shortest_path = aco.run()
print("Najkrótsza ścieżka: ", shortest_path)

# Dodanie wyświetlania ścieżek
all_paths = aco.gen_all_paths()
aco.display_paths(all_paths)