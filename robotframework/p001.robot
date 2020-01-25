*** Settings ***
Library     Collections

*** Variables ***

${LIMIT}    10

*** Test Cases ***
P001 Sample - Brute Force
    @{numbers} =    Create List
    : FOR    ${INDEX}    IN RANGE    1    ${LIMIT}
    \    ${REMAINDER_3} =    Evaluate    ${INDEX} % 3
    \    ${REMAINDER_5} =    Evaluate    ${INDEX} % 5
    \    Run Keyword If     (${REMAINDER_3} == 0 or ${REMAINDER_5} == 0)     Append to List  ${numbers}  ${INDEX}
    Log List    ${numbers}
    ${NUM_SUM} =    Evaluate    sum(@{numbers})
    Log    ${NUM_SUM}
    Should Be Equal as Integers       23    ${NUM_SUM}

P001 Below 1000 - Brute Force
    ${LIMIT} =      Set Variable    1000
    @{numbers} =    Create List
    : FOR    ${INDEX}    IN RANGE    1    ${LIMIT}
    \    ${REMAINDER_3} =    Evaluate    ${INDEX} % 3
    \    ${REMAINDER_5} =    Evaluate    ${INDEX} % 5
    \    Run Keyword If     (${REMAINDER_3} == 0 or ${REMAINDER_5} == 0)     Append to List  ${numbers}  ${INDEX}
    #Log List    ${numbers}
    ${NUM_SUM} =    Evaluate    sum(@{numbers})
    Log    ${NUM_SUM}
    Should Be Equal as Integers       233168    ${NUM_SUM}