
// getting repo
document.addEventListener('DOMContentLoaded', function() {
    const repoNumberElement = document.getElementById('repo-number');

    fetch('https://api.github.com/orgs/XenonCommunity/repos')
        .then(response => response.json())
        .then(data => {
            const repoCount = data.length;
            repoNumberElement.textContent = repoCount;
            console.info("repos loaded - script.js")
            console.warn("Website created by kilosheet")
        })
        .catch(error => {
            console.error('Error fetching repository count:', error);
            repoNumberElement.textContent = 'Error';
        });
});
