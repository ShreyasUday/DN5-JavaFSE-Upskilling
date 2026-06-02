// ============================================
// COMMUNITY EVENT PORTAL
// ============================================

console.log("Welcome to the Community Portal");

// ============================================
// PAGE LOAD
// ============================================

window.onload = () => {

    alert("Community Portal Loaded Successfully");

    loadEvents();

    loadSavedPreference();

};

// ============================================
// EVENT CLASS
// ============================================

class Event {

    constructor(
        id,
        name,
        category,
        date,
        location,
        seats
    ) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.date = date;
        this.location = location;
        this.seats = seats;
    }

    checkAvailability() {
        return this.seats > 0;
    }

}

// ============================================
// GLOBAL VARIABLES
// ============================================

let events = [];

// ============================================
// CLOSURE
// ============================================

function registrationCounter() {

    let count = 0;

    return function () {

        count++;

        console.log(
            `Total Registrations: ${count}`
        );

        return count;
    };

}

const countRegistration =
    registrationCounter();

// ============================================
// LOAD EVENTS
// ============================================

async function loadEvents() {

    try {

        const response =
            await fetch("events.json");

        const data =
            await response.json();

        events = data.map(item =>
            new Event(
                item.id,
                item.name,
                item.category,
                item.date,
                item.location,
                item.seats
            )
        );

        renderEvents(events);

        populateDropdown();

    }

    catch (error) {

        console.error(
            "Unable to load events",
            error
        );

    }

}

// ============================================
// DISPLAY EVENTS
// ============================================

function renderEvents(eventArray) {

    const container =
        document.getElementById(
            "eventsContainer"
        );

    container.innerHTML = "";

    eventArray.forEach(event => {

        const card =
            document.createElement("div");

        card.className =
            "col-md-4 mb-4";

        card.innerHTML = `

        <div class="eventCard h-100">

            <h3>${event.name}</h3>

            <p>
                <strong>Category:</strong>
                ${event.category}
            </p>

            <p>
                <strong>Date:</strong>
                ${event.date}
            </p>

            <p>
                <strong>Location:</strong>
                ${event.location}
            </p>

            <p>
                <strong>Seats:</strong>
                ${event.seats}
            </p>

            <button
                class="btn btn-primary register-event"
                data-id="${event.id}">
                Register
            </button>

        </div>

        `;

        container.appendChild(card);

    });

    attachRegisterEvents();

}

// ============================================
// REGISTER BUTTONS
// ============================================

function attachRegisterEvents() {

    document
        .querySelectorAll(
            ".register-event"
        )
        .forEach(btn => {

            btn.onclick = () => {

                const id =
                    parseInt(
                        btn.dataset.id
                    );

                registerUser(id);

            };

        });

}

// ============================================
// REGISTER USER
// ============================================

function registerUser(eventId) {

    try {

        const event =
            events.find(
                e => e.id === eventId
            );

        if (!event)
            throw new Error(
                "Event not found"
            );

        if (event.seats <= 0)
            throw new Error(
                "No seats available"
            );

        event.seats--;

        countRegistration();

        renderEvents(events);

        alert(
            `Registered for ${event.name}`
        );

    }

    catch (error) {

        console.error(
            error.message
        );

    }

}

// ============================================
// POPULATE DROPDOWN
// ============================================

function populateDropdown() {

    const select =
        document.getElementById(
            "eventSelect"
        );

    select.innerHTML =
        `<option value="">
        Select Event
        </option>`;

    events.forEach(event => {

        const option =
            document.createElement(
                "option"
            );

        option.value =
            event.name;

        option.textContent =
            event.name;

        select.appendChild(option);

    });

}

// ============================================
// SEARCH EVENTS
// ============================================

document
.getElementById("searchBox")
.addEventListener("keyup", e => {

    const value =
        e.target.value
        .toLowerCase();

    const filtered =
        events.filter(event =>
            event.name
            .toLowerCase()
            .includes(value)
        );

    renderEvents(filtered);

});

// ============================================
// FILTER CATEGORY
// ============================================

document
.getElementById("categoryFilter")
.addEventListener("change", e => {

    const category =
        e.target.value;

    if (category === "All") {

        renderEvents(events);

        return;
    }

    const filtered =
        events.filter(
            event =>
            event.category === category
        );

    renderEvents(filtered);

});

// ============================================
// PHONE VALIDATION
// ============================================

document
.getElementById("phone")
.addEventListener(
    "blur",
    () => {

        const phone =
            document
            .getElementById("phone")
            .value;

        const regex =
            /^[0-9]{10}$/;

        if (
            phone &&
            !regex.test(phone)
        ) {

            alert(
                "Enter valid 10 digit phone number"
            );

        }

    }
);

// ============================================
// CHARACTER COUNTER
// ============================================

document
.getElementById("feedback")
.addEventListener(
    "keyup",
    e => {

        document
            .getElementById(
                "charCount"
            )
            .textContent =
            e.target.value.length;

    }
);

// ============================================
// FORM SUBMISSION
// ============================================

document
.getElementById(
    "registrationForm"
)
.addEventListener(
    "submit",
    async e => {

        e.preventDefault();

        const name =
            document
            .getElementById("name")
            .value;

        const email =
            document
            .getElementById("email")
            .value;

        const selectedEvent =
            document
            .getElementById(
                "eventSelect"
            )
            .value;

        localStorage.setItem(
            "preferredEvent",
            selectedEvent
        );

        const output =
            document
            .getElementById(
                "outputMessage"
            );

        output.innerHTML =
            "Registration Submitted Successfully";

        const userData = {

            name,
            email,
            selectedEvent

        };

        try {

            const response =
                await fetch(
                    "https://jsonplaceholder.typicode.com/posts",
                    {
                        method: "POST",

                        headers: {
                            "Content-Type":
                                "application/json"
                        },

                        body:
                            JSON.stringify(
                                userData
                            )
                    }
                );

            const result =
                await response.json();

            console.log(result);

        }

        catch (error) {

            console.error(error);

        }

    }
);

// ============================================
// LOCAL STORAGE
// ============================================

function loadSavedPreference() {

    const saved =
        localStorage.getItem(
            "preferredEvent"
        );

    if (!saved)
        return;

    setTimeout(() => {

        const dropdown =
            document
            .getElementById(
                "eventSelect"
            );

        dropdown.value = saved;

    }, 500);

}

// ============================================
// CLEAR STORAGE
// ============================================

document
.getElementById(
    "clearStorage"
)
.addEventListener(
    "click",
    () => {

        localStorage.clear();

        sessionStorage.clear();

        alert(
            "Preferences Cleared"
        );

    }
);

// ============================================
// VIDEO EVENTS
// ============================================

const video =
    document.getElementById(
        "promoVideo"
    );

video.oncanplay = () => {

    document
        .getElementById(
            "videoStatus"
        )
        .textContent =
        "Video ready to play";

};

// ============================================
// WARNING BEFORE LEAVING
// ============================================

window.onbeforeunload =
function () {

    return "Form data may be lost.";

};

// ============================================
// IMAGE DOUBLE CLICK
// ============================================

document
.querySelectorAll(
    ".gallery-img"
)
.forEach(img => {

    img.ondblclick = () => {

        if (
            img.style.transform ===
            "scale(1.5)"
        ) {

            img.style.transform =
                "scale(1)";

        }

        else {

            img.style.transform =
                "scale(1.5)";

        }

    };

});

// ============================================
// GEOLOCATION
// ============================================

document
.getElementById(
    "locationBtn"
)
.addEventListener(
    "click",
    () => {

        if (
            !navigator.geolocation
        ) {

            alert(
                "Geolocation unsupported"
            );

            return;
        }

        navigator.geolocation
            .getCurrentPosition(

                position => {

                    document
                        .getElementById(
                            "locationResult"
                        )
                        .innerHTML =

                        `
Latitude:
${position.coords.latitude}

<br>

Longitude:
${position.coords.longitude}
`;

                },

                error => {

                    document
                        .getElementById(
                            "locationResult"
                        )
                        .innerHTML =
                        error.message;

                },

                {
                    enableHighAccuracy:
                        true,

                    timeout: 10000
                }

            );

    }
);

// ============================================
// OBJECT ENTRIES DEMO
// ============================================

const sampleEvent = {

    name:
        "Community Meetup",

    category:
        "Workshop",

    location:
        "Kolkata"

};

Object.entries(
    sampleEvent
).forEach(
    ([key, value]) =>
        console.log(
            key,
            value
        )
);

// ============================================
// ARRAY METHODS DEMO
// ============================================

const sampleEvents = [

    "Music Night",
    "Workshop",
    "Sports Day"

];

const formatted =
    sampleEvents.map(
        item =>
        `Event: ${item}`
    );

console.log(formatted);