const API_BASE_URL = ''
const A_Z_LETTERS = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
//rows 0-5. 0 at top
let activeRow = 0;
//characters 0-4. 0 on left
let activeChar = 0;

let correctChars = ['','','','',''];
let containsChars= ['','','','',''];
let currentUserGuess = '';
let gameSolutionID = 0;
let alertBox;
let winner = false;
let refreshButton;


document.addEventListener('DOMContentLoaded', () => {
    const letterKeys = document.querySelectorAll('.letter-key');
    alertBox = document.querySelector('.alert-box');
    refreshButton = document.querySelector('.refresh-button');
    
    //add listeners to all on-screen-keyboard keys
    letterKeys.forEach(el => el.addEventListener('click', event => {
        if(!winner){inputController(event.target.innerText)};
    }));

    //add listeners for physical keyboard
    document.addEventListener('keydown', function (event) {
        let key = event.key.toUpperCase();
        if(!winner){
            if(A_Z_LETTERS.includes(key)){
                inputController(key);
            }
            else if(key == 'BACKSPACE'){
                inputController('DEL');
            }
            else if(event.key == 'Enter'){
                inputController('ENTER');
            }
        };
      });   

    refreshButton.addEventListener('click', (event) => {
        if(!winner){
            revealWord();
        }
        winner = false;
        resetBoard();
        getNewWord();
    });
    getNewWord();

});

// function keyboardParse(eventKey){
//     let keyEvent = eventKey;
//     console.log(keyEvent.to
// }

function inputController(key){
    //clear alert area on any keypress
    alertBox.innerText = '';
    let wordLine = document.getElementById('word-line-'+ activeRow);
    let letters = wordLine.querySelectorAll('.letter-box')

    //delete characters as allowed and removes box styling
    //remove character from currentUserGuess
    if(key === 'DEL'){
        if(activeChar>0){
            activeChar--;
            letters[activeChar].innerText = '';
            updateCurrentBoxStyle('delete');
            currentUserGuess = currentUserGuess.slice(0,-1);
            alertBox.innerText = '';
        }
    }
    //submit a complete line
    else if((key == "ENTER") && (activeChar >= 4)){
        //call to check the word
        //show green/yellow/gray
        //update keyboard
        //switch to next line
        checkWord(currentUserGuess);
    }
    //ignore enter for incomplete line
    else if((key == "ENTER")){
        //do nothing
    }
    //add characters on current line as allowed
    //will need to check if letter was already chosen and 'contains (yellow)' or 'correct placement (green)
    //update button class as-required
    //concatenate character to currentUserGuess
    else if(activeChar<5){
        if(correctChars[activeChar]==key){
            updateCurrentBoxStyle('correct');
        }
        else if(containsChars[activeChar].includes(key)){
            updateCurrentBoxStyle('contains');
        }
        else{
            updateCurrentBoxStyle('filled');
        }
        letters[activeChar].innerText = key;
        currentUserGuess += key;
        activeChar++;
    }
    
}

//updates the styling of the current editing box
function updateCurrentBoxStyle(status, charToUpdate = activeChar){
    const currentWordLine = document.getElementById('word-line-'+activeRow);
    const currentLetterBoxes = currentWordLine.getElementsByClassName('letter-box');
    if(status == 'correct'){
        currentLetterBoxes[charToUpdate].classList.add('letter-box-correct');
        currentLetterBoxes[charToUpdate].classList.remove('letter-box-incorrect');
    }
    else if(status == 'contains'){
        currentLetterBoxes[charToUpdate].classList.add('letter-box-contains');
        currentLetterBoxes[charToUpdate].classList.remove('letter-box-incorrect');
    }
    else if(status == 'delete'){
        currentLetterBoxes[charToUpdate].classList.remove('letter-box-contains');
        currentLetterBoxes[charToUpdate].classList.remove('letter-box-correct');
        currentLetterBoxes[charToUpdate].classList.remove('letter-box-incorrect');
        currentLetterBoxes[charToUpdate].classList.remove('letter-box-filled');
    }
    else if(status == 'incorrect'){
        currentLetterBoxes[charToUpdate].classList.add('letter-box-incorrect');
    }
    else if(status == 'filled'){
        currentLetterBoxes[charToUpdate].classList.add('letter-box-filled');
    }
}

//updates the keyboard styling
function updateKeyStyle(key, status){
    const keyToUpdate = document.getElementById(key);
    
    if(status == 'correct'){
        keyToUpdate.classList.add('letter-key-correct');
    }
    else if(status == 'contains'){
        keyToUpdate.classList.add('letter-key-contains');
    }
    else if(status == 'incorrect'){
        keyToUpdate.classList.add('letter-key-dormant');
    }

}

function resetBoard(){
    let letterBoxes = document.querySelectorAll('.letter-box');
    let letterKeys = document.querySelectorAll('.letter-key');
    letterKeys.forEach((key) => {
        key.classList.remove('letter-key-dormant');
        key.classList.remove('letter-key-contains');
        key.classList.remove('letter-key-correct');
    });

    letterBoxes.forEach((box) => {
        box.classList.remove('letter-box-contains');
        box.classList.remove('letter-box-correct');
        box.classList.remove('letter-box-incorrect');
        box.classList.remove('letter-box-filled');
        box.innerText = ' ';
    });
    activeRow = 0;
    activeChar = 0;
    currentUserGuess = '';
    correctChars = ['','','','',''];
    containsChars= ['','','','',''];
}

function getNewWord(){
    fetch(API_BASE_URL+'new').then(result => {
            result.json().then(data => setSolutionID(data));
        }).catch(err => {
            // if any error occurred, then catch it here
            console.error(err);
        });
}

function setSolutionID(id){
    gameSolutionID = id;
}

function checkWord(userGuess){
    //call endpoint to validate guess
    //if valid, do stuff

    fetch(API_BASE_URL+'guess', {
        method:"POST",  
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
          },
        body: JSON.stringify({guess: userGuess, solutionId: gameSolutionID, correctChars: correctChars, containsChars: containsChars})
        }).then(result => {result.json().then(data => showAnswer(data));
            
        }).catch(err => {
            // if any error occured, then catch it here
            console.error(err);
        });
}

function revealWord(){
    fetch(API_BASE_URL+'solution', {
        method:"POST",  
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
          },
        body: JSON.stringify({solutionId: gameSolutionID})
        }).then(result => {result.json().then(data => displaySolution(data));
            
        }).catch(error=>{
            console.error(error);
          });
}

function displaySolution(word){
    alertBox.innerText = 'The word was: ' + word[0];
}

function showAnswer(guessValidation){
    let allLettersCorrect = true;
    if(guessValidation[0]==-1){
        //not a valid 5 letter word
        alertBox.innerText = 'Not a valid word.';
    }
    else{
        for (let i = 0; i < 5; i++) {

            if(guessValidation[i] == 2){
                
                updateCurrentBoxStyle('correct',i);
                updateKeyStyle(currentUserGuess.charAt(i),'correct');
                correctChars[i] = currentUserGuess[i];
            }
            else if(guessValidation[i] == 1){
                updateCurrentBoxStyle('contains',i);
                updateKeyStyle(currentUserGuess.charAt(i),'contains');
                containsChars[i] += currentUserGuess[i];
                allLettersCorrect = false;
            }
            else if(guessValidation[i] == 3){
                updateCurrentBoxStyle('incorrect',i);
                allLettersCorrect = false;
            }
            else{
                updateCurrentBoxStyle('incorrect',i);
                updateKeyStyle(currentUserGuess.charAt(i),'incorrect');
                allLettersCorrect = false;
            }
        }

        if(allLettersCorrect){
            winner = true;
            alertBox.innerText = 'Well done!';
        }
        activeChar = 0;
        activeRow++;
        currentUserGuess = '';
        if(activeRow>5){
            revealWord();
        }
    }
    
}
