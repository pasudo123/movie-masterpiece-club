
import moment from 'moment';

function foo() {
    return moment().format('YYYY-MM-DD hh:mm:ss a');
}


export {
    foo
}