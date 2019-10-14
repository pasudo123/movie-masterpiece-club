
import moment from 'moment';

function convertDate2Hangul(registerDate) {

    let array = registerDate.split('-');
    return `${array[0]}년 ${array[1]}월 ${array[2]}일`;
}

export {
    convertDate2Hangul
}