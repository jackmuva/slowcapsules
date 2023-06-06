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
    const [keyword, setKeyword] = useState('');

    useEffect(() => {
        const fetchSeries = async () => {
            const rsp = SeriesApi.getNewestSeries();
            const series = await rsp;
            setAllSeries(series);
        };
        fetchSeries();
    }, []);

    const updateKeyword = (keyword) => {
        console.log(keyword);
        const filtered = async () => {
            console.log("filter called");
            const rsp = SeriesApi.getSeriesByKeyword(keyword);
            const series = await rsp;
            console.log(series);
            setAllSeries(series);
        }
        filtered();
        setKeyword(keyword);
    };

  return (
      <Router>
        <div className="container">
          <Header subtitle = "Write and Subscribe to Email Series"/>
            <Navbar />
            <SeriesFilter keyword={keyword} onChange={updateKeyword}/>
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
