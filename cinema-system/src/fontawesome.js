// cinema-system/src/fontawesome.js
import {library} from '@fortawesome/fontawesome-svg-core';
import {faDollarSign, faCreditCard, faCheckCircle} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome';

library.add(faDollarSign, faCreditCard, faCheckCircle);

export {FontAwesomeIcon};