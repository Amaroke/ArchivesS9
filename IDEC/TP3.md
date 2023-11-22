# TP3

## Exercice 1

### Question 1

```
@ATTRIBUTE date			{april,may,june,july,august,september,october}
@ATTRIBUTE plant-stand 		{normal,lt-normal}
@ATTRIBUTE precip 		{lt-norm,norm,gt-norm}
@ATTRIBUTE temp 		{lt-norm,norm,gt-norm}
@ATTRIBUTE hail 		{yes,no}
@ATTRIBUTE crop-hist 		{diff-lst-year,same-lst-yr,same-lst-two-yrs, same-lst-sev-yrs}
@ATTRIBUTE area-damaged		{scattered,low-areas,upper-areas,whole-field}
@ATTRIBUTE severity 		{minor,pot-severe,severe}
@ATTRIBUTE seed-tmt 		{none,fungicide,other}
@ATTRIBUTE germination 		{90-100,80-89,lt-80}
@ATTRIBUTE plant-growth		{norm,abnorm}
@ATTRIBUTE leaves 		{norm,abnorm}
@ATTRIBUTE leafspots-halo 	{absent,yellow-halos,no-yellow-halos}
@ATTRIBUTE leafspots-marg	{w-s-marg,no-w-s-marg,dna}
@ATTRIBUTE leafspot-size	{lt-1/8,gt-1/8,dna}
@ATTRIBUTE leaf-shread 		{absent,present}
@ATTRIBUTE leaf-malf 		{absent,present}
@ATTRIBUTE leaf-mild 		{absent,upper-surf,lower-surf}
@ATTRIBUTE stem 		{norm,abnorm}
@ATTRIBUTE lodging 		{yes,no}
@ATTRIBUTE stem-cankers 	{absent,below-soil,above-soil,above-sec-nde}
@ATTRIBUTE canker-lesion 	{dna,brown,dk-brown-blk,tan}
@ATTRIBUTE fruiting-bodies 	{absent,present}
@ATTRIBUTE external-decay 	{absent,firm-and-dry,watery}
@ATTRIBUTE mycelium 		{absent,present}
@ATTRIBUTE int-discolor 	{none,brown,black}
@ATTRIBUTE sclerotia 		{absent,present}
@ATTRIBUTE fruit-pods 		{norm,diseased,few-present,dna}
@ATTRIBUTE fruit-spots		{absent,colored,brown-w/blk-specks,distort,dna}
@ATTRIBUTE seed 		{norm,abnorm}
@ATTRIBUTE mold-growth 		{absent,present}
@ATTRIBUTE seed-discolor	{absent,present}
@ATTRIBUTE seed-size 		{norm,lt-norm}
@ATTRIBUTE shriveling 		{absent,present}
@ATTRIBUTE roots 		{norm,rotted,galls-cysts}
@ATTRIBUTE class 		{diaporthe-stem-canker, charcoal-rot, rhizoctonia-root-rot, phytophthora-rot, brown-stem-rot, powdery-mildew, downy-mildew, brown-spot, bacterial-blight, bacterial-pustule, purple-seed-stain, anthracnose, phyllosticta-leaf-spot, alternarialeaf-spot, frog-eye-leaf-spot, diaporthe-pod-&-stem-blight, cyst-nematode, 2-4-d-injury, herbicide-injury}	
```

### Question 2

Avec J48, sur l'ensemble d'apprentissage :
Taux d'erreur : 3.6603 %
F-Score : 0.962
en validation croisée à 10 folds : 
Taux d'erreur : 8.4919 %
F-score : 0.913

Avec Jrip, sur l'ensemble d'apprentissage :
Taux d'erreur : 3.9531 %
F-score : 0,960
en validation croisée à 10 folds :
Taux d'erreur : 7.7599 %
F-score : 0,922

### Question 3

Avec RandomForest, sur l'ensemble d'apprentissage :
Taux d'erreur : 0.1464 %
F-Score : 0,999
en validation croisée à 10 folds : 
Taux d'erreur : 7.0278 %
F-score : 0,930

Avec adaBoostM1 random-tree, sur l'ensemble d'apprentissage :
Taux d'erreur : 0.1464 %
F-Score : 0,999  
en validation croisée à 10 folds : 
Taux d'erreur : 10.981  %
F-score : 0,890

Avec adaBoostM1 JRIP, sur l'ensemble d'apprentissage :
Taux d'erreur : 0.1464 %
F-Score : 0,999
en validation croisée à 10 folds : 
Taux d'erreur : 7.1742 %
F-score : 0,928

Les modèles RandomForest et adaBoostM1 random-tree ont les meilleurs performances mais sont plus longs à calculer et sont plus complexes, trop complexes, ils apprennent 'par coeur' le modèle données, ils font donc de mauvais classifieur en finalité.

### Question 4

Ils peuvent surapprendre les particularités de l'ensemble d'apprentissage, ce qui peut conduire à une performance médiocre sur de nouveaux ensembles de données.

### Question 5

Pour des ensembles de données de petite taille, la validation croisée à 10 folds peut ne pas être suffisante.

## Exercice 2

### Question 1

cf test.csv

### Question 2

### Question 3
