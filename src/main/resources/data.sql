create table if not exists carti(
    ISBN integer auto_increment primary key,
    autorul varchar(30) not null,
    titlul varchar(50) not null
);

insert into carti(autorul,titlul) values ("Yuval Noah Harari","Scurta istorie a omenirii");
insert into carti(autorul,titlul) values ("Yuval Noah Harari","Homo deus - Scurta istorie a viitorului");
insert into carti(autorul,titlul) values ("J.D. SALINGER","De veghe in lanul de secara");