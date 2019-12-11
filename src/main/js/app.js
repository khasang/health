'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

const follow = require('./follow'); // function to hop multiple links by "rel"

const root = '/api';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {schedules: [], attributes: [], pageSize: 2, links: {}};
        this.updatePageSize = this.updatePageSize.bind(this);
        this.onCreate = this.onCreate.bind(this);
        this.onDelete = this.onDelete.bind(this);
        this.onNavigate = this.onNavigate.bind(this);
    }

    // tag::follow-2[]
    loadFromServer(pageSize) {
        follow(client, root, [
            {rel: 'schedules', params: {size: pageSize}}]
        ).then(scheduleCollection => {
            return client({
                method: 'GET',
                path: scheduleCollection.entity._links.profile.href,
                headers: {'Accept': 'application/schema+json'}
            }).then(schema => {
                this.schema = schema.entity;
                return scheduleCollection;
            });
        }).done(scheduleCollection => {
            this.setState({
                schedules: scheduleCollection.entity._embedded.schedules,
                attributes: Object.keys(this.schema.properties),
                pageSize: pageSize,
                links: scheduleCollection.entity._links});
        });
    }
    // end::follow-2[]

    // tag::create[]
    onCreate(newSchedule) {
        follow(client, root, ['schedule']).then(scheduleCollection => {
            return client({
                method: 'POST',
                path: scheduleCollection.entity._links.self.href,
                entity: newSchedule,
                headers: {'Content-Type': 'application/json'}
            })
        }).then(response => {
            return follow(client, root, [
                {rel: 'schedules', params: {'size': this.state.pageSize}}]);
        }).done(response => {
            if (typeof response.entity._links.last !== "undefined") {
                this.onNavigate(response.entity._links.last.href);
            } else {
                this.onNavigate(response.entity._links.self.href);
            }
        });
    }
    // end::create[]

    // tag::delete[]
    onDelete(schedule) {
        client({method: 'DELETE', path: schedule._links.self.href}).done(response => {
            this.loadFromServer(this.state.pageSize);
        });
    }
    // end::delete[]

    // tag::navigate[]
    onNavigate(navUri) {
        client({method: 'GET', path: navUri}).done(scheduleCollection => {
            this.setState({
                schedules: scheduleCollection.entity._embedded.schedules,
                attributes: this.state.attributes,
                pageSize: this.state.pageSize,
                links: scheduleCollection.entity._links
            });
        });
    }
    // end::navigate[]

    // tag::update-page-size[]
    updatePageSize(pageSize) {
        if (pageSize !== this.state.pageSize) {
            this.loadFromServer(pageSize);
        }
    }
    // end::update-page-size[]

    // tag::follow-1[]
    componentDidMount() {
        this.loadFromServer(this.state.pageSize);
    }
    // end::follow-1[]

    render() {
        return (
            <div>
            <CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
        <ScheduleList schedules={this.state.schedules}
        links={this.state.links}
        pageSize={this.state.pageSize}
        onNavigate={this.onNavigate}
        onDelete={this.onDelete}
        updatePageSize={this.updatePageSize}/>
        </div>
    )
    }
}

// tag::create-dialog[]
class CreateDialog extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        const newSchedule = {};
        this.props.attributes.forEach(attribute => {
            newSchedule[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onCreate(newSchedule);

        // clear out the dialog's inputs
        this.props.attributes.forEach(attribute => {
            ReactDOM.findDOMNode(this.refs[attribute]).value = '';
        });

        // Navigate away from the dialog to hide it.
        window.location = "#";
    }

    render() {
        const inputs = this.props.attributes.map(attribute =>
            <p key={attribute}>
            <input type="text" placeholder={attribute} ref={attribute} className="field"/>
            </p>
    );

        return (
            <div>
            <a href="#createSchedule">Create</a>

            <div id="createSchedule" className="modalDialog">
            <div>
            <a href="#" title="Close" className="close">X</a>

            <h2>Create new schedule</h2>

        <form>
        {inputs}
        <button onClick={this.handleSubmit}>Create</button>
            </form>
            </div>
            </div>
            </div>
    )
    }

}
// end::create-dialog[]

class ScheduleList extends React.Component {

    constructor(props) {
        super(props);
        this.handleNavFirst = this.handleNavFirst.bind(this);
        this.handleNavPrev = this.handleNavPrev.bind(this);
        this.handleNavNext = this.handleNavNext.bind(this);
        this.handleNavLast = this.handleNavLast.bind(this);
        this.handleInput = this.handleInput.bind(this);
    }

    // tag::handle-page-size-updates[]
    handleInput(e) {
        e.preventDefault();
        const pageSize = ReactDOM.findDOMNode(this.refs.pageSize).value;
        if (/^[0-9]+$/.test(pageSize)) {
            this.props.updatePageSize(pageSize);
        } else {
            ReactDOM.findDOMNode(this.refs.pageSize).value =
                pageSize.substring(0, pageSize.length - 1);
        }
    }
    // end::handle-page-size-updates[]

    // tag::handle-nav[]
    handleNavFirst(e){
        e.preventDefault();
        this.props.onNavigate(this.props.links.first.href);
    }

    handleNavPrev(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.prev.href);
    }

    handleNavNext(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.next.href);
    }

    handleNavLast(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.last.href);
    }
    // end::handle-nav[]

    // tag::schedule-list-render[]
    render() {
        const schedules = this.props.schedules.map(schedule =>
            <Schedule key={schedule._links.self.href} schedule={schedule} onDelete={this.props.onDelete}/>
    );

        const navLinks = [];
        if ("first" in this.props.links) {
            navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
        }
        if ("prev" in this.props.links) {
            navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
        }
        if ("next" in this.props.links) {
            navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
        }
        if ("last" in this.props.links) {
            navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
        }

        return (
            <div>
            <input ref="pageSize" defaultValue={this.props.pageSize} onInput={this.handleInput}/>
        <table>
        <tbody>
        <tr>
        <th>Name</th>
        <th>Monday <input type="text"  className="field1"/> </th>

        <th>Tuesday <tr><input type="text"  className="field2"/></tr> </th>

        <th>Wednesday <tr><input type="text"  className="field3"/></tr></th>

        <th>Thirday <tr><input type="text"  className="field4"/></tr></th>

        <th>Friday <tr><input type="text"  className="field5"/></tr></th>

        <th>Saturday</th>
            <tr><input type="text"  className="field6"/></tr>
        <th>Sunday</th>
            <tr><input type="text"  className="field7"/></tr>
        <th></th>
        </tr>


        {schedules}
    </tbody>
        </table>
        <div>
        {navLinks}
        </div>
        </div>
    )
    }
    // end::schedule-list-render[]
}

// tag::schedule[]
class Schedule extends React.Component {

    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete() {
        this.props.onDelete(this.props.schedule);
    }

    render() {
        return (
            <tr>
            <td>{this.props.schedule.firstName}</td>
            <td>{this.props.schedule.lastName}</td>
            <td>{this.props.schedule.description}</td>
            <td>
            <button onClick={this.handleDelete}>Delete</button>
            </td>
            </tr>
    )
    }
}
// end::schedule[]

ReactDOM.render(
<App />,
    document.getElementById('react')
)
