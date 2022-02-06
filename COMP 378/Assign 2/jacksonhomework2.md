2b
- computed values. Ex: instead of having to query for "COUNT * FROM FRIENDS"
  you would store the computed total count for faster lookup
  Computations can be expensive... so caching a computed value can be faster lookup

2c
- wide tables run the risk of a lot of wasted space... NULL everywhere.
  if there are optional values, then there could be a lot of wasted space. would be better to put optional values in a separate table and left join

- vertical partioning sounds like you are descibing a CDN

4b
Indexing against the keys... Employee.emp-no, and Proj.emp-no/Proj.proj-no
WARNING. dont typically index against numeric values, ex: Salary. It is an infinite amount of things.
usually best to be indexing against keys, and then finite things, otherwise your poor SQL engine stats are all gonna be wrong

it is crucial the things you link against (the keys) are indexed. then indexes after usually merely end up hinting the SQL engine. but depending on what you choose, after about 1-2 levels deep of indexing, it stops carrying the actual index and cares about an average statistic overall (it no longer looks like a dictionary lookup, rather, it says... ~100 records on average). Then the stats engine could be wrong because maybe there are 1000000000 and it estimates incorrectly. This is why indexing against numbers usually isnt an ideal thing...

5d
INNER JOIN

instead of just 'sub' use the 'AS sub'. AS helps makes things a bit more readable that something is being aliased... yes jackson... it all translates to computer 1s and 0s the same... but we write sql for people.

5f
SELECT ta.age
FROM TRAVEL_AGENT AS ta
INNER JOIN (
    SELECT c.name, tt.travel_agent_name
    FROM CUSTOMER AS c
    INNER JOIN (
        SELECT t.cust_name, t.travel_agent_name
        FROM TRANSACTION AS t
    ) AS tt
    ON c.name = tt.cust_name
        AND c.destination = 'Ottawa'
        AND c.name LIKE '%John Smith'
) AS ct
ON ta.name = ct.travel_agent_name

5g
because you are using multiple tables here...
they need to be aliased. because once you get to the 
WHERE clause, how does it know cust_name and destination come from?
SQL may not be that smart.

Also, you could apply the WHERE directly on the ON with ANDS
like my subqueries above. more performant.


