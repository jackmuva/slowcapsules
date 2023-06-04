import './App.css';
import Header from "./components/Header/header";
import { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import SeriesPage from "./components/main-page/series-page";
import SeriesApi from "./api/SeriesApi";
import Navbar from "./components/Navbar"

function App() {
    const [allSeries, setAllSeries] = useState([]);
    useEffect(() => {
        const fetchSeries = async () => {
            const rsp = SeriesApi.getNewestSeries();
            const series = await rsp
            setAllSeries(series);
            console.log(series);

        };
        fetchSeries();
    }, []);

  return (
      <Router>
        <div className="container">
          <Header subtitle = "Write and Subscribe to Email Series"/>
            <Navbar />
            <Switch>
                <Route path = "/">
                    <SeriesPage allSeries = {allSeries}></SeriesPage>
                </Route>
            </Switch>
        </div>
      </Router>
  );
}

export default App;
