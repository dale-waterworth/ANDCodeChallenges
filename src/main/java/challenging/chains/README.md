# Challenging Chains
### Task

For a chain which is considered ‘unbroken’, all parts of the chain can be reached without exiting the chain. For example, in this diagram:
```
#######
#
#
```

there is 1 unbroken chain. However, in this diagram:
```
### #######

          #
```
there are actually 3 distinct unbroken chains.
Your task is to write a program which can determine how many separate chains exist in this input problem. You can assume that links can only be connected vertically and horizontally (not diagonally) and that no two chains touch one another.

## Approach
 Use TDD to work from a single # then
  - Traverse X axis then include gaps
  - Traverse Y axis then include gaps
  - Traverse X and Y axis top to bottom
    - At this stage meta data was introduced as it got tricky to check the links around
  - Traverse X and Y axis from right to left
  - Traverse X and Y axis from bottom to top
  - At this stage i simply plugged in the file and it worked first time 💪

## To run
- check out the project
- install dependancies (`mvn install`)
- open the test and click run

## Result
chains = 34
time = 41ms (0.041s)

![alt text](https://github.com/dale-waterworth/ANDCodeChallenges/tree/master/src/main/resources/img.png "Test Result")
