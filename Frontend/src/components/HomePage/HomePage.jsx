import DailyForecast from "../DailyForecast/DailyForecast";
import "./HomePage.css"

function HomePage(props){
    console.log(props)
    return(
        <div className={"container"}>
                <div className={"table-group"}>
                    <i className={"description"}>This table shows all sunny days (maximum temperature greater than 25
                        degrees)</i>
                    <table className={"table"} id={"sunny"}>
                        <thead>
                        <tr>
                            <th>City</th>
                            <th>Date</th>
                            <th>Max Temp</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.sunnyDays.map((term)=> {
                            return(
                                <DailyForecast key={term.id} city = {term.city} date={term.date} maxTemp={term.maxTemp} desc={term.rainDesc}> </DailyForecast>
                                )

                        })}
                        </tbody>
                    </table>
                </div>
                <div className={"table-group"}>
                    <i className={"description"}>This table shows all sunny days (maximum temperature greater than 25
                        degrees)</i>
                    <table className={"table"} id={"rainy"}>
                        <thead>
                        <tr>
                            <th>City</th>
                            <th>Date</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.rainyDays.map((term)=> {
                            return(
                                <DailyForecast key={term.id} city = {term.city} date={term.date} desc={term.rainDesc}> </DailyForecast>
                            )

                        })}
                        </tbody>
                    </table>
                </div>

        </div>
    )

}

export default HomePage;