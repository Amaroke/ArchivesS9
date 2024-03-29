# 1
 
```
use mathieus1u
db.createCollection("movies")
```

# 2

```
db.movies.insertOne({
    "titre": "Star Wars, épisode I : La Menace fantôme",
    "realisateur": "George Lucas"
})
```

```
{
  "acknowledged": true,
  "insertedId": {
    "$oid": "6569df43f67c3a71e0a29bb7"
  }
}
```

# 3

```
db.books.insertMany([
    {
        "title": "The Hobbit; or, There and Back Again",
        "author": "J. R. R. Tolkien",
        "description": "Now a major motion picture\n A great modern classic and the prelude to THE LORD OF THE RINGS\n Bilbo Baggins is a hobbit who enjoys a comfortable, unambitious life, rarely traveling any farther than his pantry or cellar. But his contentment is disturbed when the wizard Gandalf and a company of dwarves arrive on his doorstep one day to whisk him away on an adventure. They have launched a plot to raid the treasure hoard guarded by Smaug the Magnificent, a large and very dangerous dragon. Bilbo reluctantly joins their quest, unaware that on his journey to the Lonely Mountain he will encounter both a magic ring and a frightening creature known as Gollum.\n “A glorious account of a magnificent adventure, filled with suspense and seasoned with a quiet humor that is irresistible . . . All those, young or old, who love a fine adventurous tale, beautifully told, will take The Hobbit to their hearts.” – New York Times Book Review",
        "format": {
            "kindle": {
                "shop": [
                    "Amazon"
                ],
                "price": 8.50
            },
            "hardcover": {
                "shop": [
                    "Amazon",
                    "Fnac"
                ],
                "price": 12.49
            },
            "paperback": {
                "shop": [
                    "Amazon",
                    "Fnac"
                ],
                "price": 11.13
            },
            "audiobook CD": {
                "shop": [
                    "Fnac"
                ],
                "price": 28.09
            }
        }
    },
    {
        "title": "The Silmarillion",
        "author": "J. R. R. Tolkien",
        "editor": "Christopher Tolkien",
        "description": "A number-one New York Times bestseller when it was originally published, THE SILMARILLION is the core of J.R.R. Tolkien's imaginative writing, a work whose origins stretch back to a time long before THE HOBBIT.\n Tolkien considered THE SILMARILLION his most important work, and, though it was published last and posthumously, this great collection of tales and legends clearly sets the stage for all his other writing. The story of the creation of the world and of the the First Age, this is the ancient drama to which the characters in THE LORD OF THE RINGS look back and in whose events some of them, such as Elrond and Galadriel, took part. The three Silmarils were jewels created by Feanor, most gifted of the Elves. Within them was imprisoned the Light of the Two Trees of Valinor before the Trees themselves were destroyed by Morgoth, the first Dark Lord. Thereafter, the unsullied Light of Valinor lived on only in the Silmarils, but they were seized by Morgoth and set in his crown, which was guarded in the impenetrable fortress of Angband in the north of Middle-earth. THE SILMARILLION is the history of the rebellion of Feanor and his kindred against the gods, their exile from Valinor and return to Middle-earth, and their war, hopeless despite all their heroism, against the great Enemy.\n This second edition features a letter written by J.R.R. Tolkien describing his intentions for the book, which serves as a brilliant exposition of his conception of the earlier Ages of Middle-earth.",
        "format": {
            "kindle": {
                "shop": [
                    "Amazon"
                ],
                "price": 8.56
            },
            "hardcover": {
                "shop": [
                    "Amazon"
                ],
                "price": 13.74
            },
            "paperback": {
                "shop": [
                    "Amazon"
                ],
                "price": 10.72
            },
            "audiobook CD": {
                "shop": [
                    "Amazon"
                ],
                "price": 44.06
            }
        }
    },
    {
        "title": "The Fellowship of the Ring: Being the First Part of The Lord of the Rings",
        "author": "J. R. R. Tolkien",
        "description": "The first volume in J.R.R. Tolkien's epic adventure THE LORD OF THE RINGS\n One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them\n In ancient times the Rings of Power were crafted by the Elven-smiths, and Sauron, the Dark Lord, forged the One Ring, filling it with his own power so that he could rule all others. But the One Ring was taken from him, and though he sought it throughout Middle-earth, it remained lost to him. After many ages it fell into the hands of Bilbo Baggins, as told in The Hobbit. In a sleepy village in the Shire, young Frodo Baggins finds himself faced with an immense task, as his elderly cousin Bilbo entrusts the Ring to his care. Frodo must leave his home and make a perilous journey across Middle-earth to the Cracks of Doom, there to destroy the Ring and foil the Dark Lord in his evil purpose.\n“A unique, wholly realized other world, evoked from deep in the well of Time, massively detailed, absorbingly entertaining, profound in meaning.” – New York Times",
        "format": {
            "kindle": {
                "shop": [
                    "Amazon"
                ],
                "price": 5.38
            },
            "hardcover": {
                "shop": [
                    "Fnac"
                ],
                "price": 16.92
            },
            "paperback": {
                "shop": [
                    "Amazon",
                    "Fnac"
                ],
                "price": 10.66
            },
            "audiobook CD": {
                "shop": [
                    "Amazon"
                ],
                "price": 16.95
            }
        }
    },
    {
        "title": "Tolkien's World from A to Z: The Complete Guide to Middle-Earth",
        "author": "Robert Foster",
        "description": "For the millions who have already ventured to Middle-earth, and for the countless others who have yet to embark on the journey–here is the one indispensable A-to-Z guide that brings Tolkien’s universe to life.\n\n EVERY CHARACTER\n From Adaldrida Brandybuck to Zaragamba–every Hobbit, Elf, Dwarf, Man, Orc, or other resident of Middle-earth is vividly described and accurately located in proper place and time.\n\n EVERY PLACE\n Colorfully detailed descriptions of geographical entries allow you to pick up the action anywhere in Middle-earth and follow it through all five volumes.\n\n EVERY THING\n From stars and streams to food and flora, everything found in Middle-earth is alphabetically listed and, when necessary, cross-referenced.\n\n HERE IS TRULY A MASTER KEY\nTO TOLKIEN’S MIDDLE-EARTH",
        "format": {
            "hardcover": {
                "shop": [
                    "Fnac"
                ],
                "price": 28.98
            },
            "paperback": {
                "shop": [
                    "Fnac"
                ],
                "price": 11.83
            }
        }
    },
    {
        "title": "The Two Towers: Being the Second Part of The Lord of the Rings",
        "author": "J. R. R. Tolkien",
        "description": "The second volume in J.R.R. Tolkien's epic adventure THE LORD OF THE RINGS\n One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them\n Frodo and his Companions of the Ring have been beset by danger during their quest to prevent the Ruling Ring from falling into the hands of the Dark Lord by destroying it in the Cracks of Doom. They have lost the wizard, Gandalf, in a battle in the Mines of Moria. And Boromir, seduced by the power of the Ring, tried to seize it by force. While Frodo and Sam made their escape, the rest of the company was attacked by Orcs. Now they continue the journey alone down the great River Anduin—alone, that is, save for the mysterious creeping figure that follows wherever they go.\n\n“Among the greatest works of imaginative fiction of the twentieth century. The book presents us with the richest profusion of new lands and creatures, from the beauty of Lothlórien to the horror of Mordor.” – Sunday Telegraph",
        "format": {
            "kindle": {
                "shop": [
                    "Amazon"
                ],
                "price": 7.44
            },
            "hardcover": {
                "shop": [
                    "Amazon",
                    "Fnac"
                ],
                "price": 15.16
            },
            "paperback": {
                "shop": [
                    "Amazon",
                    "Fnac"
                ],
                "price": 10.59
            },
            "audiobook CD": {
                "shop": [
                    "Amazon",
                    "Fnac"
                ],
                "price": 42.74
            }
        }
    }
])
```

```
{
  "acknowledged": true,
  "insertedIds": {
    "0": {
      "$oid": "6569df7233d6c25dfc6da78f"
    },
    "1": {
      "$oid": "6569df7233d6c25dfc6da790"
    },
    "2": {
      "$oid": "6569df7233d6c25dfc6da791"
    },
    "3": {
      "$oid": "6569df7233d6c25dfc6da792"
    },
    "4": {
      "$oid": "6569df7233d6c25dfc6da793"
    }
  }
}
```

# 4

```
db.books.find({
    author: "J. R. R. Tolkien"
})
```

```
[
  {
    "_id": {
      "$oid": "6569df7233d6c25dfc6da78f"
    },
    "title": "The Hobbit; or, There and Back Again",
    "author": "J. R. R. Tolkien",
    "description": "Now a major motion picture\n A great modern classic and the prelude to THE LORD OF THE RINGS\n Bilbo Baggins is a hobbit who enjoys a comfortable, unambitious life, rarely traveling any farther than his pantry or cellar. But his contentment is disturbed when the wizard Gandalf and a company of dwarves arrive on his doorstep one day to whisk him away on an adventure. They have launched a plot to raid the treasure hoard guarded by Smaug the Magnificent, a large and very dangerous dragon. Bilbo reluctantly joins their quest, unaware that on his journey to the Lonely Mountain he will encounter both a magic ring and a frightening creature known as Gollum.\n “A glorious account of a magnificent adventure, filled with suspense and seasoned with a quiet humor that is irresistible . . . All those, young or old, who love a fine adventurous tale, beautifully told, will take The Hobbit to their hearts.” – New York Times Book Review",
    "format": {
      "kindle": {
        "shop": [
          "Amazon"
        ],
        "price": 8.5
      },
      "hardcover": {
        "shop": [
          "Amazon",
          "Fnac"
        ],
        "price": 12.49
      },
      "paperback": {
        "shop": [
          "Amazon",
          "Fnac"
        ],
        "price": 11.13
      },
      "audiobook CD": {
        "shop": [
          "Fnac"
        ],
        "price": 28.09
      }
    }
  },
  {
    "_id": {
      "$oid": "6569df7233d6c25dfc6da790"
    },
    "title": "The Silmarillion",
    "author": "J. R. R. Tolkien",
    "editor": "Christopher Tolkien",
    "description": "A number-one New York Times bestseller when it was originally published, THE SILMARILLION is the core of J.R.R. Tolkien's imaginative writing, a work whose origins stretch back to a time long before THE HOBBIT.\n Tolkien considered THE SILMARILLION his most important work, and, though it was published last and posthumously, this great collection of tales and legends clearly sets the stage for all his other writing. The story of the creation of the world and of the the First Age, this is the ancient drama to which the characters in THE LORD OF THE RINGS look back and in whose events some of them, such as Elrond and Galadriel, took part. The three Silmarils were jewels created by Feanor, most gifted of the Elves. Within them was imprisoned the Light of the Two Trees of Valinor before the Trees themselves were destroyed by Morgoth, the first Dark Lord. Thereafter, the unsullied Light of Valinor lived on only in the Silmarils, but they were seized by Morgoth and set in his crown, which was guarded in the impenetrable fortress of Angband in the north of Middle-earth. THE SILMARILLION is the history of the rebellion of Feanor and his kindred against the gods, their exile from Valinor and return to Middle-earth, and their war, hopeless despite all their heroism, against the great Enemy.\n This second edition features a letter written by J.R.R. Tolkien describing his intentions for the book, which serves as a brilliant exposition of his conception of the earlier Ages of Middle-earth.",
    "format": {
      "kindle": {
        "shop": [
          "Amazon"
        ],
        "price": 8.56
      },
      "hardcover": {
        "shop": [
          "Amazon"
        ],
        "price": 13.74
      },
      "paperback": {
        "shop": [
          "Amazon"
        ],
        "price": 10.72
      },
      "audiobook CD": {
        "shop": [
          "Amazon"
        ],
        "price": 44.06
      }
    }
  },
  {
    "_id": {
      "$oid": "6569df7233d6c25dfc6da791"
    },
    "title": "The Fellowship of the Ring: Being the First Part of The Lord of the Rings",
    "author": "J. R. R. Tolkien",
    "description": "The first volume in J.R.R. Tolkien's epic adventure THE LORD OF THE RINGS\n One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them\n In ancient times the Rings of Power were crafted by the Elven-smiths, and Sauron, the Dark Lord, forged the One Ring, filling it with his own power so that he could rule all others. But the One Ring was taken from him, and though he sought it throughout Middle-earth, it remained lost to him. After many ages it fell into the hands of Bilbo Baggins, as told in The Hobbit. In a sleepy village in the Shire, young Frodo Baggins finds himself faced with an immense task, as his elderly cousin Bilbo entrusts the Ring to his care. Frodo must leave his home and make a perilous journey across Middle-earth to the Cracks of Doom, there to destroy the Ring and foil the Dark Lord in his evil purpose.\n“A unique, wholly realized other world, evoked from deep in the well of Time, massively detailed, absorbingly entertaining, profound in meaning.” – New York Times",
    "format": {
      "kindle": {
        "shop": [
          "Amazon"
        ],
        "price": 5.38
      },
      "hardcover": {
        "shop": [
          "Fnac"
        ],
        "price": 16.92
      },
      "paperback": {
        "shop": [
          "Amazon",
          "Fnac"
        ],
        "price": 10.66
      },
      "audiobook CD": {
        "shop": [
          "Amazon"
        ],
        "price": 16.95
      }
    }
  },
  {
    "_id": {
      "$oid": "6569df7233d6c25dfc6da793"
    },
    "title": "The Two Towers: Being the Second Part of The Lord of the Rings",
    "author": "J. R. R. Tolkien",
    "description": "The second volume in J.R.R. Tolkien's epic adventure THE LORD OF THE RINGS\n One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them\n Frodo and his Companions of the Ring have been beset by danger during their quest to prevent the Ruling Ring from falling into the hands of the Dark Lord by destroying it in the Cracks of Doom. They have lost the wizard, Gandalf, in a battle in the Mines of Moria. And Boromir, seduced by the power of the Ring, tried to seize it by force. While Frodo and Sam made their escape, the rest of the company was attacked by Orcs. Now they continue the journey alone down the great River Anduin—alone, that is, save for the mysterious creeping figure that follows wherever they go.\n\n“Among the greatest works of imaginative fiction of the twentieth century. The book presents us with the richest profusion of new lands and creatures, from the beauty of Lothlórien to the horror of Mordor.” – Sunday Telegraph",
    "format": {
      "kindle": {
        "shop": [
          "Amazon"
        ],
        "price": 7.44
      },
      "hardcover": {
        "shop": [
          "Amazon",
          "Fnac"
        ],
        "price": 15.16
      },
      "paperback": {
        "shop": [
          "Amazon",
          "Fnac"
        ],
        "price": 10.59
      },
      "audiobook CD": {
        "shop": [
          "Amazon",
          "Fnac"
        ],
        "price": 42.74
      }
    }
  }
]
```

# 5

```
db.books.find({
    "format.paperback.shop": "Fnac",
    "format.paperback.price": {
        $lt: 11.00
    }
},
    {
        "title": 1
    }).sort("title")
```

```
[
  {
    "_id": {
      "$oid": "6569df7233d6c25dfc6da791"
    },
    "title": "The Fellowship of the Ring: Being the First Part of The Lord of the Rings"
  },
  {
    "_id": {
      "$oid": "6569df7233d6c25dfc6da793"
    },
    "title": "The Two Towers: Being the Second Part of The Lord of the Rings"
  }
]
```

# 6

```
db.books.find({
    $or: [
        {
            "format.kindle.price": {
                $lt: 6.00
            }
        },
        { "format.kindle": { $exists: false } }
    ]
})
```

```
[
  {
    "_id": {
      "$oid": "6569df7233d6c25dfc6da791"
    },
    "title": "The Fellowship of the Ring: Being the First Part of The Lord of the Rings",
    "author": "J. R. R. Tolkien",
    "description": "The first volume in J.R.R. Tolkien's epic adventure THE LORD OF THE RINGS\n One Ring to rule them all, One Ring to find them, One Ring to bring them all and in the darkness bind them\n In ancient times the Rings of Power were crafted by the Elven-smiths, and Sauron, the Dark Lord, forged the One Ring, filling it with his own power so that he could rule all others. But the One Ring was taken from him, and though he sought it throughout Middle-earth, it remained lost to him. After many ages it fell into the hands of Bilbo Baggins, as told in The Hobbit. In a sleepy village in the Shire, young Frodo Baggins finds himself faced with an immense task, as his elderly cousin Bilbo entrusts the Ring to his care. Frodo must leave his home and make a perilous journey across Middle-earth to the Cracks of Doom, there to destroy the Ring and foil the Dark Lord in his evil purpose.\n“A unique, wholly realized other world, evoked from deep in the well of Time, massively detailed, absorbingly entertaining, profound in meaning.” – New York Times",
    "format": {
      "kindle": {
        "shop": [
          "Amazon"
        ],
        "price": 5.38
      },
      "hardcover": {
        "shop": [
          "Fnac"
        ],
        "price": 16.92
      },
      "paperback": {
        "shop": [
          "Amazon",
          "Fnac"
        ],
        "price": 10.66
      },
      "audiobook CD": {
        "shop": [
          "Amazon"
        ],
        "price": 16.95
      }
    }
  },
  {
    "_id": {
      "$oid": "6569df7233d6c25dfc6da792"
    },
    "title": "Tolkien's World from A to Z: The Complete Guide to Middle-Earth",
    "author": "Robert Foster",
    "description": "For the millions who have already ventured to Middle-earth, and for the countless others who have yet to embark on the journey–here is the one indispensable A-to-Z guide that brings Tolkien’s universe to life.\n\n EVERY CHARACTER\n From Adaldrida Brandybuck to Zaragamba–every Hobbit, Elf, Dwarf, Man, Orc, or other resident of Middle-earth is vividly described and accurately located in proper place and time.\n\n EVERY PLACE\n Colorfully detailed descriptions of geographical entries allow you to pick up the action anywhere in Middle-earth and follow it through all five volumes.\n\n EVERY THING\n From stars and streams to food and flora, everything found in Middle-earth is alphabetically listed and, when necessary, cross-referenced.\n\n HERE IS TRULY A MASTER KEY\nTO TOLKIEN’S MIDDLE-EARTH",
    "format": {
      "hardcover": {
        "shop": [
          "Fnac"
        ],
        "price": 28.98
      },
      "paperback": {
        "shop": [
          "Fnac"
        ],
        "price": 11.83
      }
    }
  }
]
```

# 7

```
db.books.find({ "description": { "$regex": "\.*dwar\.*", "$options": "i" } }).count()
```

2

# 8 

```
db.books.updateOne(
    { "title": "The Hobbit; or, There and Back Again" },
    {
        $set:
        {
            "format.kindle.price": 7.00,
            "personnage": ["Gandalf", "Bilbon", "Elrond"]
        }
    }
)
```

```
{
  "acknowledged": true,
  "insertedId": null,
  "matchedCount": 1,
  "modifiedCount": 1,
  "upsertedCount": 0
}
```

# 9

```
db.books.updateOne(
    { "title": "Tolkien's World from A to Z: The Complete Guide to Middle-Earth" },
    {
        $push:
        {
            "format.paperback.shop": "Cultura"
        }
    }
)
```

# 10

```
db.books.deleteMany(
    {
        "personnages": { $exists: true },
        "editor": { $exists: true }
    }
)
```

```
{
  "acknowledged": true,
  "deletedCount": 0
}
```

# 11

```
db.books.createIndex(
    {
        "title": 1
    }
)
```

title_1

# 12

```
db.books.drop()
```

```
db.books.aggregate({
    $group:
    {
        _id: "$author",
        moyenne: {
            $avg: "$format.kindle.price"
        }
    }
})
```

```
[
  {
    "_id": "J. R. R. Tolkien",
    "moyenne": 7.095000000000001
  },
  {
    "_id": "Robert Foster",
    "moyenne": null
  }
]
```

# 13

```
db.books.aggregate([
  {
    $match: {
      "format.paperback": { $exists: true }
    }
  },
  {
    $unwind: "$format.paperback.shop"
  },
  {
    $group: {
      _id: "$format.paperback.shop",
      count: { $sum: 1 }
    }
  },
  {
    $out: "books_count_per_shop"
  }
]);
```

# 14

```
```db.books.aggregate([
    {
        $match: {
            "format.paperback.shop": "Amazon"
        }
    },
    {
        $group: {
            _id: "$title",
            count: { $sum: 1 }
        }
    },
    {
        $out: "books_amazon"
    }
]);```
```

```
[
  {
    "_id": "The Hobbit; or, There and Back Again",
    "count": 1
  },
  {
    "_id": "The Silmarillion",
    "count": 1
  },
  {
    "_id": "The Two Towers: Being the Second Part of The Lord of the Rings",
    "count": 1
  },
  {
    "_id": "The Fellowship of the Ring: Being the First Part of The Lord of the Rings",
    "count": 1
  }
]
```

# 15

```python
from pymongo import MongoClient

client = MongoClient("localhost", 27017)
db = client["mathieus1u"]

pipeline = [
    {"$match": {"format.paperback.shop": "Amazon"}},
    {"$group": {"_id": "$title", "count": {"$sum": 1}}},
    {"$out": "books_amazon_aggregate_python"},
]

result = db.books.aggregate(pipeline)

for doc in db.books_amazon_aggregate_python.find():
    print(doc["_id"])
```