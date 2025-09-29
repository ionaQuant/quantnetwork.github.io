---
title: Built-in Functions
parent: Payscript
layout: page
---

# Built-in Functions
PayScript offers built-in functions as the interface to interact with banking systems, as well as other utilities. This is the comprehensive list of functions available, and the data types used in them.


## Account Functions
## `BalanceInfo getBalance(AccountInfo accountIdentifier)`
Shows the total balance and available balance for an account.


## Payment Functions
## `UUID requestPayment(String closureId, RequestToPayInfo requestToPay)`
Sends a payment request from one account to another.

## `PaymentResponseDTO createPayment(String closureId, PaymentInfo paymentInfo)`
Makes a payment between two accounts based on `paymentInfo`.


## Reserve Functions
## `UUID reserveTransactionId addFundsToReserve(String closureId, AccountInfo fromAccount, String toReserve, BigDecimal amount, String reference)`
Add funds from user's account to a reserve. 

## `UUID reserveId createReserve(String closureId, Reserve reserve)`
Creates a reserve for an account.

## `void deleteReserve(UUID reserveId)`
Deletes the reserve with ID `reserveId`.

## `UUID reserveId editReserve(UpdateReserveInfo updateReserveInfo)`
Applies the update defined in the object [`updateReserveInfo`](#updatereserveinfo).

## `List<reserveInfo> getAccountReserves(AccountIdentifier accountIdentifier)`
Gets the list of all account reserves.

## `ReserveInfo reserve getReserveInfo(String reserveId)`
Get a reserve’s details.

## `List<ReserveTransaction> transactions getReserveTransactions(UUID reserveId, ReserveTransactionsFilter filter, Integer offset, Integer limit)`
Get the list of reserve’s transactions, based on specified filters, ordered by date (latest transactions first in the list)

## `Integer totalTransactions getReserveTransactionsCount(UUID reserveId, ReserveTransactionsFilter filter)`
Get total number of a reserve’s transactions, based on specified filters

## Utility Functions

## `Object callScript(String scriptId,  List<Map<String, Object>>... params)`
Triggers a script which has trigger type "sub_script", passing `params` as its list of map of parameter names to parameters.

## `void logMessage(String message)`
Logs the messages inside the script

# Data Objects
Data objects are used as parameters in some built-in functions. Their attributes can be accessed by default getters and setters. The data objects can be instantiated through the builder pattern. For example:
```groovy
def payment = PaymentInfo.builder()
        .payer(AccountInfo.builder()
                .type(AccountType.SCAN)
                .accountIdentifier(payer)
                .accountHolderName(name)
                .build())
        .payee(AccountInfo.builder()
                .type(AccountIdentifierType.SCAN)
                .accountIdentifier(payee)
                .accountHolderName(name)
                .build())
        .amountInfo(AmountInfo.builder()
                .currency(CurrencyEnum.GBP)
                .amount(amount)
                .build())
        .reason("This is a payment.")
        .build();
```

## `BalanceInfo`
Attributes:<br>
`BigDecimal totalBalance`: A total balance of funds in an account, which includes funds in locks and reserves.<br>
`BigDecimal availableBalance`: The balance of funds in an account which is not committed to locks or reserves.<br>
`Currency currency`

## `PaymentInfo`
Attributes:<br>
`AccountInfo  payer`: The account providing funds<br>
`AccountInfo  payee`: The account receiving funds<br>
`AmountInfo amount`: Amount of funds being transferred<br>
`String reason`: Text that should provide context for the payment<br>

## `RequestToPayInfo`
Attributes:<br>
`AccountInfo  payer`: The account providing funds<br>
`AccountInfo  payee`: The account receiving funds<br>
`AmountInfo amount`: Amount of funds being transferred<br>
`String reason`: Text that should provide context for the payment<br>

## `AccountInfo`
Attributes:<br>
`String  accountIdentifier`
`AccountType  accountType`
`String accountHolderName`

## `AmountInfo`
Attributes:<br>
`BigDecimal  amount`
`Currency  currency`

## `AccountType`
Enum values:<br>
`{ SCAN,IBAN,PAN, BICSWIFT}`

## `SCAN`
The account identifiers used within the UK Domestic Banking System.
Attributes:<br>
`String sortCode`<br>
`String accountNumber`<br>

## `PAN`
The **P**rimary **A**ccount **N**umber, or Payment Card Number, used as the identifier of payment cards, as defined in [ISO/IEC 7812-1:2017 Identification cards — Identification of issuers](https://www.iso.org/standard/70484.html)<br>
Attributes:<br>
`String pan`

## `IBAN`
The **I**nternational **B**ank **A**ccount **N**umber, used as a standard in cross-border payments within participating countries, as defined in [ISO 13616-1:2020 Financial services — International bank account number (IBAN)](https://www.iso.org/standard/81090.html)<br>
Attributes:<br>
`String iban`

## `BicSwift`
The Business Identifier Code (BIC), used in a Swift network as defined in [ISO 9362:2022 Banking — Banking telecommunication messages — Business identifier code (BIC)](https://www.iso.org/standard/84108.html)<br>
Attributes:<br>
`String bic`
`String swift`

## `Currency`
Enum values:<br>
`{ GBP,EUR}`

## `Reserve`
Attributes:<br>
`String reserveId`: The UUID of the existing reserve<br>
`String name`: The name to be applied<br>
`Integer priority`: The priority number to be applied<br>
`BigInteger SavingGoal`: The goal of savings to be applied<br>
`String description`: The description to be applied<br>

## `ReserveTransactionsFilter`
Attributes:<br>
`String fromReserve`
`String toReserve`
`Instant fromDate`
`Instant toDate`

## `TriggerTransactionDetails`
Attributes:<br>
`AccountInfo payer`
`AccountInfo payee`
`BigDecimal savingGoal`
`String description`

## `ReserveInfo`
Attributes:<br>
`UUID reserveId`: The UUID of the existing reserve<br>
`AccountInfo account`: The account from the reserve<br>
`String name`: The name from the saved reserve<br>
`BigDecimal balance`
`Integer priority`: The priority number from the reserve<br>
`BigInteger savingGoal`: The goal of savings from the reserve<br>
`String description`: The saved description<br>
`Instant createdAt`:
`Instant updtedAt`

## `UpdateReserveInfo`
Creates an object containing values to use to update a reserve's attributes. See an [example of reserve update][updateReserve].<br>
Attributes:<br>
`String reserveId`: The UUID of the existing reserve<br>
`String name`: The name to be applied with the update<br>
`Integer priority`: The priority number to be applied with the update<br>
`BigInteger savingGoal`: The goal of savings to be applied with the update<br>
`String description`: The description to be applied with the update<br>

## `ReserveTnxHistoryResponseDTO`
Attributes:<br>
`UUID id`
`TransactionParty payer`
`TransactionParty payee`
`BigDecimal amount`
`String reference`
`Insant createdAt`

## `TransactionParty`
Attributes:<br>
`UUID id`
`PartyType type`
`String name`

## `PartyType`
Enum values:<br>
`{ ACCOUNT,RESERVE}`

[updateReserve]: example_scripts/update_reserve
[reserve]: /docs/quant_flow/concepts#reserve
