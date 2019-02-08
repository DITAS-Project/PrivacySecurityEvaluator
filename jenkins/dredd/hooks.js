var hooks = require('hooks');


skipBodyValidation = ['privacy-security-evaluator > /v1/filter > filter > 200 > application/json;charset=UTF-8','/filter > legacy-filter > 200 > application/json;charset=UTF-8'];

skipBodyValidation.forEach(function(transactionName){
  hooks.beforeValidation(transactionName, function(transaction){
    console.log('Skipping body validation for transaction "'+ transactionName +'".');
    transaction['expected']['body'] = undefined;
    transaction['expected']['bodySchema'] = undefined;
  });
});