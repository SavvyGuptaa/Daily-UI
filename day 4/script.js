let currentInput = '';
let currentOperator = '';
let currentResult = 0;

function appendNumber(number) {
  currentInput += number;
  document.getElementById('result').textContent = currentInput;
}

function appendOperator(operator) {
  currentOperator = operator;
  currentResult = currentInput;
  currentInput = '';
}

function calculate() {
  if (currentOperator === '+') {
    currentResult = parseFloat(currentResult) + parseFloat(currentInput);
  } else if (currentOperator === '-') {
    currentResult = parseFloat(currentResult) - parseFloat(currentInput);
  } else if (currentOperator === '*') {
    currentResult = parseFloat(currentResult) * parseFloat(currentInput);
  } else if (currentOperator === '/') {
    currentResult = parseFloat(currentResult) / parseFloat(currentInput);
  }

  document.getElementById('result').textContent = currentResult;
  currentInput = '';
}

function clearInput() {
  currentInput = '';
  currentOperator = '';
  currentResult = 0;
  document.getElementById('result').textContent = currentResult;
}
