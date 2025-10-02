trigger = "transaction";

var savingsAccount = ${savingsAccount:type=AccountInfo, required=true, label="Savings Account", linked=true}; 
var percentageToTransfer = ${percentageToTransfer:type=decimal, required=true, label="Percentage To Transfer"};
var saveOnTransactionsOver = ${saveOnTransactionsOver:type=decimal, required=true, label="Save On Transactions Over"};

var incomingPaymentAmount = getTriggerTransactionDetails().getAmount().getAmount();

if (incomingPaymentAmount.compareTo(saveOnTransactionsOver) < 0) {
    return;
}

var amountToTransfer = incomingPaymentAmount.multiply(percentageToTransfer).divide(100);
var currentAccount = getAutomationOwnerAccountInfo();
var transfer = PaymentInfo.builder()
        .payer(currentAccount)
        .payee(savingsAccount)
        .amount(AmountInfo.builder()
                .currency(Currency.GBP)
                .amount(amountToTransfer)
                .build())
        .reason("Credit Optimisation")
        .build();

createPayment("e70fefd2-c52a-46c2-848d-cd7a6d9bcacd",transfer);
