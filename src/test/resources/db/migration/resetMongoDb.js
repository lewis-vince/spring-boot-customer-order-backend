var conn = new Mongo();
var db = conn.getDB("spring-boot-store-test-data");

var orderCollection = db.getCollection("orders");
// remove all documents
orderCollection.remove({});
// add documents
orderCollection.insertMany(
    [
        {
            _id: "5bec3a870c8c140c60c5f405",
            customerId: 45,
            orderDate: "2018-11-11 14:42:14.000",
            items: []
        },
        {
            _id: "5bec3a870c8c140c60c5f406",
            customerId: 32,
            orderDate: "2018-11-01 21:22:45.000",
            items: []
        },
        {
            _id: "5bec3a870c8c140c60c5f407",
            customerId: 45,
            orderDate: "2017-05-23 08:00:45.000",
            items: []
        }
    ]
);