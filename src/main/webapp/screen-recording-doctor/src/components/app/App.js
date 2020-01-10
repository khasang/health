import React from 'react';
import s from './App.module.css';
import {BrowserRouter} from "react-router-dom";
import Header from "./header/Header";
import Content from "./content/Content";

const App = (props) => {
    props.state.content.mapDepartment.map(d => d.speciality = checkTitleSpeciality(d.speciality));
    function checkTitleSpeciality(titleSpeciality) {
        titleSpeciality = checkRegText(/\S\(/, titleSpeciality);

        function checkRegText(regExp, titleSpeciality) {
            let i = 0;
            while (bRegText(regExp, titleSpeciality)) {
                let match = regExp.exec(titleSpeciality);
                titleSpeciality = titleSpeciality.substring(0, match.index + 1) + ' ' + titleSpeciality.substring(match.index + 1);

                i++;
                if (i > 10) {
                    console.log("WARNING while -> break");
                    break;
                }
            }
            return titleSpeciality;
        }

        function bRegText(regExp, text) {
            return regExp.exec(text);
        }

        return titleSpeciality;
    }


    return (
        <BrowserRouter>
            <div className={s.app_wrapper}>
                <Header state={props.state.header}/>
                <Content state={props.state.content}/>
            </div>
        </BrowserRouter>
    );
};

export default App;
