import './App.css';
import Header from "./components/Header/header";
import { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import SeriesPage from "./components/main-page/series-page";
import SeriesApi from "./api/SeriesApi";
import Navbar from "./components/Navbar";
import SeriesFilter from "./components/main-page/series-filer";

function App() {
    const [allSeries, setAllSeries] = useState([]);
    const [searchResults, setSearchResults] = useState([]);

    useEffect(() => {
        const fetchSeries = async () => {
            const rsp = SeriesApi.getNewestSeries();
            const series = await rsp;
            setAllSeries(series);
            setSearchResults(series);
            console.log("effect called");
        };
        fetchSeries();
    }, []);

  return (
      <Router>
        <div className="container">
          <Header subtitle = "Write and Subscribe to Email Series"/>
            <Navbar />
            <SeriesFilter posts={allSeries} setSearchResults={setSearchResults}/>
            <Switch>
                <Route path = "/">
                    <SeriesPage allSeries = {searchResults}></SeriesPage>
                </Route>
            </Switch>
        </div>
      </Router>
  );
}

export default App;
