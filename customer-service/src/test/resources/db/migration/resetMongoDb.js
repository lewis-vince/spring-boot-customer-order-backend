var conn = new Mongo();
var db = conn.getDB("customer-db-test");

// -----------------------------------------------------------------------
// CUSTOMERS
// -----------------------------------------------------------------------

var customerCollection = db.getCollection("customers");
// remove all documents from collection
customerCollection.remove({});
// add test documents
customerCollection.insertMany(
    [
        {
            _id: "TOYS_000001",
            businessName: "Toys 'R' Us",
            contactName: "Mr John Toymaker",
            contactNumber: "01438 562443",
            address: "45 Weston Industrial Estate, Plymouth, PE54 8HG"
        },
        {
            _id: "SPEC_000002",
            businessName: "Specsavers",
            contactName: "Mr Ivan Seegood",
            contactNumber: "08533 394876",
            address: "Handel House, Northup Lane, Nottingham, NG5 3RO"
        },
        {
            _id: "HEIN_000003",
            businessName: "Heinz",
            contactName: "Mr Beansauce McHeinz",
            contactNumber: "08555 992338",
            address: "Heinz HQ, Sidgaard Lane, London, L4 7HH"
        },
    ]
);