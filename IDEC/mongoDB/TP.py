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
