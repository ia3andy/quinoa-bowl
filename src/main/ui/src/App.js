import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";

async function fetchGreeting(onData) {
  const result = await fetch('/hello');
  onData(await result.text());
}

function App() {

  const [hello, setHello] = useState();

  useEffect(() => {
    if(!hello) {
      fetchGreeting(setHello).catch(console.error);
    }
  }, [hello, setHello]);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p className="quinoa">
          {hello || 'loading...'}
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
