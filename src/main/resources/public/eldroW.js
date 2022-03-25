
//rows 0-5. 0 at top
let activeRow = 0;
//characters 0-4. 0 on left
let activeChar = 0;

let correctChars = ["","","","",""];
let containsChars= ['','','','',''];
let currentUserGuess = '';
let gameSolutionID = 0;


document.addEventListener('DOMContentLoaded', () => {
    const letterKeys = document.querySelectorAll('.letter-key');

    letterKeys.forEach(el => el.addEventListener('click', event => {
        inputController(event.target);
    }));

    getNewWord();

});



function inputController(key){
    console.log(key.innerText);
    console.log('active char: ' + activeChar);

    let wordLine = document.getElementById('word-line-'+ activeRow);
    let letters = wordLine.querySelectorAll('.letter-box')

    //delete characters as allowed and removes box styling
    //remove character from currentUserGuess
    if(key.innerText === 'DEL'){
        if(activeChar>0){
            activeChar--;
            letters[activeChar].innerText = '';
            updateCurrentBoxStyle('delete');
            currentUserGuess = currentUserGuess.slice(0,-1);
        }
    }
    //submit a complete line
    else if((key.innerText == "ENTER") && (activeChar >= 4)){
        //call to check the word
        //show green/yellow/gray
        //update keyboard
        //switch to next line
        checkWord(currentUserGuess);
    }
    //ignore enter for incomplete line
    else if((key.innerText == "ENTER")){
        //do nothing
    }
    //add characters on current line as allowed
    //will need to check if letter was already chosen and 'contains (yellow)' or 'correct placement (green)
    //update button class as-required
    //concatenate character to currentUserGuess
    else if(activeChar<5){
        if(correctChars[activeChar]==key.innerText){
            console.log('correct letter');
            updateCurrentBoxStyle('correct');
        }
        else if(containsChars[activeChar]==key.innerText){
            updateCurrentBoxStyle('contains');
        }
        letters[activeChar].innerText = key.innerText;
        currentUserGuess += key.innerText;
        activeChar++;
    }
}



//updates the styling of the current editing box
function updateCurrentBoxStyle(status, charToUpdate = activeChar){
    const currentWordLine = document.getElementById('word-line-'+activeRow);
    const currentLetterBoxes = currentWordLine.getElementsByClassName('letter-box');
    if(status == 'correct'){
        currentLetterBoxes[charToUpdate].classList.add('letter-box-correct');
    }
    else if(status == 'contains'){
        currentLetterBoxes[charToUpdate].classList.add('letter-box-contains');
    }
    else if(status == 'delete'){
        currentLetterBoxes[charToUpdate].classList.remove('letter-box-contains');
        currentLetterBoxes[charToUpdate].classList.remove('letter-box-correct');
    }
}

function getNewWord(){
    fetch('http://localhost:8080/new').then(result => {
            result.json().then(data => setSolutionID(data));
        }).catch(err => {
            // if any error occurred, then catch it here
            console.error(err);
        });
}

function setSolutionID(id){
    gameSolutionID = id;
    console.log(gameSolutionID);
}

function setLastGuessResponse(guessValidation){
    console.log('active row: ' + activeRow);

    for (let i = 0; i < 5; i++) {
        if(guessValidation[i] == 2){
            updateCurrentBoxStyle('correct',i)
        }
        else if(guessValidation[i] == 1){
            updateCurrentBoxStyle('contains',i)
        }
    }
    activeChar = 0;
    activeRow++;
    currentUserGuess = '';
}

function checkWord(userGuess){
    //call endpoint to validate guess
    //if valid, do stuff

    fetch('http://localhost:8080/guess?' + new URLSearchParams({guess:userGuess, solutionId:gameSolutionID}), {
        method:"POST"
        }).then(result => {result.json().then(data => setLastGuessResponse(data));
            
        }).catch(err => {
            // if any error occured, then catch it here
            console.error(err);
        });
}

function updateGuessStyling(){
    
    for (let i = 0; i < 5; i++) {
        if(guessValidation[i] == 2){
            updateCurrentBoxStyle('correct',i);
            correctChars[i] = currentUserGuess[i];
        }
        else if(guessValidation[i] == 1){
            updateCurrentBoxStyle('contains',i);
        }
    }
    console.log(correctChars);
}