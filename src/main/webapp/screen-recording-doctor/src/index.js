import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './components/app/App';
import * as serviceWorker from './serviceWorker';
import state from './components/state/state.js';

ReactDOM.render(<App state={state}/>, document.getElementById('root'));
serviceWorker.unregister();
