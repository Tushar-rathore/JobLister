// script.js

document.addEventListener('DOMContentLoaded', () => {
    // Add event listeners for buttons
    document.querySelector('#search button').addEventListener('click', searchPosts);
    document.querySelector('#add button').addEventListener('click', addPost);
});

function searchPosts() {
    const searchText = document.getElementById('searchInput').value.trim();
    if (searchText === '') {
        alert('Please enter a search term');
        return;
    }

    fetch(`http://localhost:8080/posts/${searchText}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to search posts');
            }
            return response.json();
        })
        .then(data => {
            displayResponse(data);
        })
        .catch(error => {
            console.error('Error searching posts:', error);
        });
}

function addPost() {
    const title = document.getElementById('titleInput').value.trim();
 profile = document.getElementById('profileInput').value.trim();
    const desc = document.getElementById('descInput').value.trim();
    const exp = parseInt(document.getElementById('expInput').value);
    const techs = document.getElementById('techsInput').value.split(',');

    if (title === ''  || profile === '' || desc === '' || isNaN(exp) || techs.length === 0) {
        alert('Please fill in all fields');
        return;
    }

    const newPost = {
        title: title,
        profile: profile,
        desc: desc,
        exp: exp,
        techs: techs
    };

    fetch('http://localhost:8080/post', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newPost),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to add post');
        }
        return response.json();
    })
    .then(data => {
        displayResponse(data);
    })
    .catch(error => {
        console.error('Error adding post:', error);
    });
}

function displayResponse(data) {
    const responseContainer = document.getElementById('response');
    responseContainer.innerHTML = JSON.stringify(data, null, 2);
}
function displayResponse(data) {
    const responseContainer = document.getElementById('response');
    responseContainer.innerHTML = ''; // Clear previous content

    data.forEach(post => {
        const postCard = document.createElement('div');
        postCard.classList.add('post-card');

        const title = document.createElement('h3');
        title.textContent = post.title;

        const description = document.createElement('p');
        description.textContent = post.description;

        const profile = document.createElement('p');
        profile.textContent = `Profile: ${post.profile}`;

        const desc = document.createElement('p');
        desc.textContent = `Description: ${post.desc}`;

        const exp = document.createElement('p');
        exp.textContent = `Experience: ${post.exp} years`;

        const techs = document.createElement('p');
        techs.textContent = `Technologies: ${post.techs.join(', ')}`;

        postCard.appendChild(title);
        postCard.appendChild(description);
        postCard.appendChild(profile);
        postCard.appendChild(desc);
        postCard.appendChild(exp);
        postCard.appendChild(techs);

        responseContainer.appendChild(postCard);
    });
}
function fetchAllPosts() {
    fetch('http://localhost:8080/allPosts')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch all posts');
            }
            return response.json();
        })
        .then(data => {
            displayResponse(data);
        })
        .catch(error => {
            console.error('Error fetching all posts:', error);
        });
}
