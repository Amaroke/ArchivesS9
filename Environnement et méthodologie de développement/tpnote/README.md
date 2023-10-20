Exécuter l'application : <br />
`mvn spring-boot:run` <br />

Exécuter les tests : <br />
`mvn test` <br />

Documentation : <br />
http://localhost:8080/swagger-ui/index.html <br />

Console BDD : <br />
http://localhost:8080/h2-console/ <br />

Emplacements des rapports de couverture de code : <br />
XXX/target/site/jacoco/index.html <br />

```
- Concernant la possibilité de modifier une image d'illustration, avez-vous fait une route dédiée au fait de remplacer la photo ou bien considérez-vous qu'il faille simplement appeler la route de suppression puis la route d'ajout ? 

    Appeler la route de suppression puis la route d'ajout est suffisant, de même un ajout par dessus une image existante est suffisant.

- Concernant le stockage des images, est-il préférable de mettre d'avoir une seule table contenant tous les IDs de toutes les images (et faire une jointure systématique donc) ou bien vaut-il mieux mettre l'ID de la photo d'illustration dans la table restaurant et ne pas mettre d'ID en DB pour l'image d'illustration d'un restaurant ?

    Les jointures peuvent ralentir les performances, il vaut donc mieux conserver l'ID et n'en faire l'appel que lorsque nécessaire.

- Lorsque vous retournez un restaurant, est-ce une bonne idée de retourner la photo systématiquement (via un lien bien sûr) ?

    Pareil cela va dépendre de l'utilisation dans le back, si on veut afficher une liste de restaurants avec des miniatures il est préférable de la renvoyer avec si on veut seulement l'afficher lorsqu'on veut regarder les détails du restaurant il vaut mieux faire la requête à ce moment là.	

- Lorsque vous retournez un restaurant, est-ce une bonne idée de retourner l'évaluation finale systématiquement ?
    
    Oui, car c'est une information importante pour l'utilisateur.
    Ou non, car cela peut être une information en trop. Donc pareil cela va dépendre de l'usage qu'on en fait dans le backend mieux vaut créer une route dédiée à l'affichage de l'évaluation finale.

- Expliquez l'implémentation que vous avez fait des tags (quelles solutions avez-vous envisagées et pourquoi avoir retenu la vôtre en particulier)

    J'ai utilisé une énumération pour les tags, car ils sont fixes et ne peuvent pas être modifiés par l'utilisateur. Cela permet de les stocker en base de données sans avoir à créer une table dédiée.
```


```

* Je n'ai pas fais de tests faute de temps.

* Je n'ai pas non plus géré la création/suppression des EvaluationFinale toujours par manque de temps. Même si l'entity existe.

* La note d'un restaurant ne se met pas à jour d'elle même (d'ailleurs elle est toujours à null) il aurait fallut la recalculer à chaque ajout d'évaluation (ou la caculer chaque fois qu'on get mais ça me semble pas être une très bonne solution). Je ne l'ai pas fais.

* On aurait pu gérer beaucoup plus d'erreurs (ça aurait d'ailleurs permis de faire beaucoup plus de tests) mais je n'ai pas eu le temps de le faire. Car en l'état je throw pratiquement toujours rien quand il y a des problèmes, au lieu de throw des erreurs customs.

* J'ai tout mis dans le même controller car toutes les routes ont besoin d'un id de restaurant, ça ne serait plus le cas si j'avais implémenté les photos d'une évaluation, comme c'est fait pour les photos d'un restaurant mais ce n'est pas implémenté.
De même la modification des evaluation aurait du être dans un controller à part.

```