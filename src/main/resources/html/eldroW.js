
//rows 0-5. 0 at top
let activeRow = 0;
//characters 0-4. 0 on left
let activeChar = 0;

let correctChars = ["M","O","I","S","T"];
let containsChars= ['O','I','','',''];
let currentUserGuess = '';


document.addEventListener('DOMContentLoaded', () => {
    const letterKeys = document.querySelectorAll('.letter-key');

    letterKeys.forEach(el => el.addEventListener('click', event => {
        inputController(event.target);

    }));
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
        activeChar = 0;
        activeRow++;
        currentUserGuess = '';
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
function updateCurrentBoxStyle(status){
    const currentWordLine = document.getElementById('word-line-'+activeRow);
    const currentLetterBoxes = currentWordLine.getElementsByClassName('letter-box');
    if(status == 'correct'){
        currentLetterBoxes[activeChar].classList.add('letter-box-correct');
    }
    else if(status == 'contains'){
        currentLetterBoxes[activeChar].classList.add('letter-box-contains');
    }
    else if(status == 'delete'){
        currentLetterBoxes[activeChar].classList.remove('letter-box-contains');
        currentLetterBoxes[activeChar].classList.remove('letter-box-correct');
    }
}

function checkWord(userGuess){
    //call endpoint to validate guess
    //if valid, do stuff
    console.log('validating the guess: ' + currentUserGuess);
    //if valid guess, but incorrect
}