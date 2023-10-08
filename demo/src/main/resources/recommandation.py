import mysql.connector
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

def get_recommandation(input_project):
    # establish a database connection
    db = mysql.connector.connect(
        host="localhost",
        user="freelancini",
        password="freelancini",
        database="freelancini",
        auth_plugin="mysql_native_password"
    )

    # define your SQL query
    query = "SELECT * FROM recommandation"

    # use the pandas read_sql() function to read data from MySQL database
    df = pd.read_sql(query, db)

    # close the database connection
    db.close()
    df.duplicated(subset='title').sum()
    df = df.drop_duplicates(subset='title')
    df['title'] = df['title'].str.lower()
    df2 = df.drop(['id','payment','description'],axis=1)
    df2.dropna(inplace=True)
    df2['data'] = df2[df2.columns[0:]].apply(
        lambda x: ' '.join(x.dropna().astype(str)),
        axis=1
    )

    vectorizer = CountVectorizer()
    vectorized = vectorizer.fit_transform(df2['data'])

    similarities = cosine_similarity(vectorized)
    df = pd.DataFrame(similarities, columns=df['title'], index=df['title']).reset_index()
    recommendations = pd.DataFrame(df.nlargest(6,input_project)['title'])
    recommendations = recommendations[recommendations['title']!=input_project]
    return recommendations['title'].tolist()