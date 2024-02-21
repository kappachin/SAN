import numpy as np


# Definicja funkcji aktywacji - tutaj użyjemy funkcji skokowej
def activation_function(x):
    return 1 if x >= 0 else 0


# Funkcja do trenowania perceptronu
def train_perceptron(X, y, learning_rate=0.1, epochs=10):
    # Inicjalizacja wag i biasu
    weights = np.zeros(X.shape[1])
    bias = 0

    # Trenowanie perceptronu
    for _ in range(epochs):
        for i in range(len(X)):
            # Obliczenie sumy ważonej i zastosowanie funkcji aktywacji
            weighted_sum = np.dot(X[i], weights) + bias
            prediction = activation_function(weighted_sum)

            # Aktualizacja wag i biasu
            error = y[i] - prediction
            weights += learning_rate * error * X[i]
            bias += learning_rate * error

    return weights, bias


# Przykładowa reprezentacja litery M i inne przykłady
M = [1, 0, 0, 0, 1,
     1, 1, 0, 1, 1,
     1, 0, 1, 0, 1,
     1, 0, 0, 0, 1,
     1, 0, 0, 0, 1]

non_M = [0, 0, 1, 0, 0,
         0, 1, 0, 1, 0,
         1, 0, 0, 0, 1,
         0, 1, 1, 1, 0,
         0, 0, 1, 0, 0]

# Dane treningowe i etykiety (1 dla "M", 0 dla "nie M")
X = np.array([M, non_M])
y = np.array([1, 0])

# Trenowanie perceptronu
weights, bias = train_perceptron(X, y)

print("Wagi:", weights)
print("Bias:", bias)


# Funkcja do testowania perceptronu
def predict(X, weights, bias):
    weighted_sum = np.dot(X, weights) + bias
    return activation_function(weighted_sum)


# Testowanie perceptronu na literze M
test_M = np.array(M)
print("Predykcja dla M:", predict(test_M, weights, bias))
