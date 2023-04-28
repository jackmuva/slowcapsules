import './App.css';
import Header from "./main-page/header";
import { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import SeriesCard from "./main-page/series-card";
import SeriesApi from "./api/SeriesApi";

function App() {
    const [allSeries, setAllSeries] = useState([]);
    useEffect(() => {
        const fetchSeries = async () => {
            const rsp = SeriesApi.getNewestSeries();
            console.log(SeriesApi.getNewestSeries());
            const series = await rsp
            setAllSeries(series);
        };
        fetchSeries();
    }, []);

  return (
      <Router>
        <div className="container">
          <Header subtitle = "Write and Subscribe to Email Series"/>
            <Switch>
                <Route path = "/">
                    <SeriesCard series = {allSeries[0]}></SeriesCard>
                </Route>
            </Switch>
        </div>
      </Router>
  );
}

export default App;
