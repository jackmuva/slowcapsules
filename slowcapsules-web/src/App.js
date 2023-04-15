import './App.css';
import Header from "./main-page/header";
import { useEffect, useState, useMemo } from "react";

function App() {
    const [allSeries, setAllSeries] = useState([]);
    useEffect(() => {
        const fetchSeries = async () => {
            const rsp = await fetch("/sample-series.json");
            const series = await rsp.json();
            setAllSeries(series);
        };
        fetchSeries();
    }, []);

  return (
    <div className="container">
      <Header subtitle = "Write and Subscribe to Email Series"/>
    </div>
  );
}

export default App;
