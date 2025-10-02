trigger="on_demand";

var reserveId = ${reserveId:type=string, required=true, label="Reserve Id"};
var name = ${name:type=string, required=false, label="Name"};
var priority = ${priority:type=int, required=false, label="Priority"};
var savingGoal = ${savingGoal:type=decimal, required=true, label="Saving Goal"};
var description = ${description:type=string, required=false, label="Description"};

var updateReserveInfo = new UpdateReserveInfo(reserveId, name, priority, savingGoal, description);
editReserve(updateReserveInfo);
