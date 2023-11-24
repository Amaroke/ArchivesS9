# TP4

## Exercice 1

### Question 1

Simple K-means clustering

```
Cluster 0: 6.1,2.9,4.7,1.4
Cluster 1: 6.2,2.9,4.3,1.3
Cluster 2: 6.9,3.1,5.1,2.3

Final cluster centroids:
                           Cluster#
Attribute      Full Data          0          1          2
                 (150.0)     (61.0)     (50.0)     (39.0)
=========================================================
sepallength       5.8433     5.8885      5.006     6.8462
sepalwidth         3.054     2.7377      3.418     3.0821
petallength       3.7587     4.3967      1.464     5.7026
petalwidth        1.1987      1.418      0.244     2.0795

=== Model and evaluation on training set ===

Clustered Instances

0       61 ( 41%)
1       50 ( 33%)
2       39 ( 26%)
```

HAC

```
Cluster 0: 6.1,2.9,4.7,1.4
Cluster 1: 6.2,2.9,4.3,1.3
Cluster 2: 6.9,3.1,5.1,2.3

Final cluster centroids:
                           Cluster#
Attribute      Full Data          0          1          2
                 (150.0)     (61.0)     (50.0)     (39.0)
=========================================================
sepallength       5.8433     5.8885      5.006     6.8462
sepalwidth         3.054     2.7377      3.418     3.0821
petallength       3.7587     4.3967      1.464     5.7026
petalwidth        1.1987      1.418      0.244     2.0795

=== Model and evaluation on training set ===

Clustered Instances

0       61 ( 41%)
1       50 ( 33%)
2       39 ( 26%)
```

### Question 2

En K-means 
```
Clustered Instances

0       61 ( 41%)
1       50 ( 33%)
2       39 ( 26%)


Class attribute: class
Classes to Clusters:

  0  1  2  <-- assigned to cluster
  0 50  0 | Iris-setosa
 47  0  3 | Iris-versicolor
 14  0 36 | Iris-virginica

Cluster 0 <-- Iris-versicolor
Cluster 1 <-- Iris-setosa
Cluster 2 <-- Iris-virginica

Incorrectly clustered instances :	17.0	 11.3333 %
```

K-means avec manhattan distance et canopy
```
Clustered Instances

0       55 ( 37%)
1       50 ( 33%)
2       45 ( 30%)


Class attribute: class
Classes to Clusters:

  0  1  2  <-- assigned to cluster
  0 50  0 | Iris-setosa
 10  0 40 | Iris-versicolor
 45  0  5 | Iris-virginica

Cluster 0 <-- Iris-virginica
Cluster 1 <-- Iris-setosa
Cluster 2 <-- Iris-versicolor

Incorrectly clustered instances :	15.0	 10      %
```

En HAC
```
Clustered Instances

0       49 ( 33%)
1        1 (  1%)
2      100 ( 67%)


Class attribute: class
Classes to Clusters:

  0  1  2  <-- assigned to cluster
 49  1  0 | Iris-setosa
  0  0 50 | Iris-versicolor
  0  0 50 | Iris-virginica

Cluster 0 <-- Iris-setosa
Cluster 1 <-- No class
Cluster 2 <-- Iris-versicolor

Incorrectly clustered instances :	51.0	 34      %
```
![HAC_COMPLETE](/HAC-SINGLE.png).

En HAC avec COMPLETE-type au lieu de SINGLE-type, les résultats sont bien meilleurs :
```
Clustered Instances

0       50 ( 33%)
1       66 ( 44%)
2       34 ( 23%)


Class attribute: class
Classes to Clusters:

  0  1  2  <-- assigned to cluster
 50  0  0 | Iris-setosa
  0 49  1 | Iris-versicolor
  0 17 33 | Iris-virginica

Cluster 0 <-- Iris-setosa
Cluster 1 <-- Iris-versicolor
Cluster 2 <-- Iris-virginica

Incorrectly clustered instances :	18.0	 12      %
```
![HAC_COMPLETE](/HAC-COMPLETE.png).

### DBSCAN

![DISTANCE](/DISTANCE-DBSCAN.png)

En eucliedienne :
Iris-setosa et Iris-virginica ≈ 4.80
Iris-setosa et Iris-versicolor ≈ 3.08
Iris-versicolor et Iris-virginica ≈ 1.82

Entre deux versicolors : 0.31 il aurait fallut en prendre plus et faire une moyenne.

Avec epsilon 0.3 et minPoint 4 :
```
Clustered Instances

0       50 ( 34%)
1       99 ( 66%)

Unclustered instances : 1

Class attribute: class
Classes to Clusters:

  0  1  <-- assigned to cluster
 50  0 | Iris-setosa
  0 50 | Iris-versicolor
  0 49 | Iris-virginica

Cluster 0 <-- Iris-setosa
Cluster 1 <-- Iris-versicolor

Incorrectly clustered instances :	49.0	 32.6667 %
```

Je n'arrive pas à trouver des paramètres qui sépare Iris-versicolor et Iris-virginica.

## Exercie 2

```
Clustered Instances

0       40 ( 19%)
1       28 ( 13%)
2        2 (  1%)
3       24 ( 11%)
4      100 ( 47%)
5       20 (  9%)


Class attribute: Type
Classes to Clusters:

  0  1  2  3  4  5  <-- assigned to cluster
 15  0  0 17 38  0 | build wind float
 21  0  0  2 42 11 | build wind non-float
  3  0  0  2 12  0 | vehic wind float
  0  0  0  0  0  0 | vehic wind non-float
  1  2  2  0  1  7 | containers
  0  3  0  0  5  1 | tableware
  0 23  0  3  2  1 | headlamps

Cluster 0 <-- vehic wind float
Cluster 1 <-- headlamps
Cluster 2 <-- No class
Cluster 3 <-- build wind float
Cluster 4 <-- build wind non-float
Cluster 5 <-- containers

Incorrectly clustered instances :	122.0	 57.0093 %
```

```
0      207 ( 97%)
1        1 (  0%)
2        1 (  0%)
3        1 (  0%)
4        1 (  0%)
5        1 (  0%)
6        2 (  1%)


Class attribute: Type
Classes to Clusters:

  0  1  2  3  4  5  6  <-- assigned to cluster
 70  0  0  0  0  0  0 | build wind float
 74  1  0  0  1  0  0 | build wind non-float
 17  0  0  0  0  0  0 | vehic wind float
  0  0  0  0  0  0  0 | vehic wind non-float
 10  0  1  0  0  0  2 | containers
  8  0  0  1  0  0  0 | tableware
 28  0  0  0  0  1  0 | headlamps

Cluster 0 <-- build wind non-float
Cluster 1 <-- No class
Cluster 2 <-- No class
Cluster 3 <-- tableware
Cluster 4 <-- No class
Cluster 5 <-- headlamps
Cluster 6 <-- containers

Incorrectly clustered instances :	136.0	 63.5514 %
```

```
Clustered Instances

0       26 ( 12%)
1       40 ( 19%)
2        2 (  1%)
3       31 ( 14%)
4       41 ( 19%)
5       74 ( 35%)


Class attribute: Type
Classes to Clusters:

  0  1  2  3  4  5  <-- assigned to cluster
 17  0  0  7  4 42 | build wind float
  5  6  0 10 34 21 | build wind non-float
  4  0  0  3  1  9 | vehic wind float
  0  0  0  0  0  0 | vehic wind non-float
  0  6  2  3  0  2 | containers
  0  4  0  4  1  0 | tableware
  0 24  0  4  1  0 | headlamps

Cluster 0 <-- vehic wind float
Cluster 1 <-- headlamps
Cluster 2 <-- containers
Cluster 3 <-- tableware
Cluster 4 <-- build wind non-float
Cluster 5 <-- build wind float

Incorrectly clustered instances :	104.0	 48.5981 %
```

Je n'ai pas réussi à trouver des paramètres qui sépare les types efficacement.