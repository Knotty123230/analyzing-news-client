document.getElementById('categorySelect').addEventListener('change', function () {
    const selectedCategory = this.value;
    console.log(selectedCategory)
    if (selectedCategory !== "Виберіть категорію") {
        fetch(`http://localhost:8081/news?category=${selectedCategory}`)
            .then(response => response.json())
            .then(data => {
                console.log(data)
                const newsContainer = document.getElementById('newsContainer');
                newsContainer.innerHTML = '';

                if (data.length > 0) {
                    data.forEach(news => {
                        const newsElement = document.createElement('div');
                        newsElement.className = 'news-item';
                        newsElement.innerHTML = `
                            <h2>${news.title}</h2>
                            <p>${news.content}</p>
                        `;
                        newsContainer.appendChild(newsElement);
                    });
                } else {
                    newsContainer.innerHTML = '<p>Новини за цією категорією відсутні.</p>';
                }
            })
            .catch(error => console.error('Error fetching news:', error));
    }
});
