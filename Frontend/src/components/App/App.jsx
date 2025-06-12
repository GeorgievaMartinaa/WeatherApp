import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import './App.css'
import {useEffect, useState} from "react";
import WeatherService from "../../repository/repository";
import React from "react";
import HomePage from "../HomePage/HomePage";

function App() {

    const [sunnyDays, setSunnyDays] = useState([]);
    const [rainyDays, setRainyDays] = useState([]);

    useEffect(() => {
        const loadData = async () => {
            await WeatherService.fetchForecasts();

            const [hotDays, rainyDays] = await Promise.all([
                WeatherService.getHotDays(),
                WeatherService.getRainyDays()
            ]);

            setSunnyDays(hotDays.data);
            setRainyDays(rainyDays.data);
        }

        loadData();
    }, []);

    useEffect(() => {
        console.log("Sunny days changed:", sunnyDays);
    }, [sunnyDays]);

    useEffect(() => {
        console.log("Rainy days changed:", rainyDays);
    }, [rainyDays]);

    return (
        <Router>
            <Routes>
                <Route path={"/"} exact element={
                     <HomePage sunnyDays={sunnyDays} rainyDays={rainyDays}/>

                }> </Route>
            </Routes>
        </Router>
    );

}


export default App
