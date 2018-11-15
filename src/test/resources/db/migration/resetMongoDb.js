var conn = new Mongo();
var db = conn.getDB("spring-boot-store-test-data");

var orderCollection = db.getCollection("orders");
// remove all documents
orderCollection.remove({});
// add documents
orderCollection.insertMany(
    [
        {
            _id: "ORDER1",
            customerId: "45",
            orderDate: new Date("2018-11-11T14:42:14")
        },
        {
            _id: "ORDER2",
            customerId: "32",
            orderDate: new Date("2018-11-01T21:22:45")
        },
        {
            _id: "ORDER3",
            customerId: "49",
            orderDate: new Date("2017-05-23T08:00:45")
        }
    ]
);