#!bin/sh
git pull origin master

read summary

git add .
git commit -m "${summary}"
git push origin master
