## ws-ws-replacement 
Workspace Whitespace Replacement is a simple Jenkins plug-in that allows job to be created with spaces in their name, but when the job is carried out on a slave node, the path used will have all spaces and encoded forward slashes replaced by an underscore.

Out of the box, Jenkins offers a couple of ways to limit or restrict characters such as spaces in workspace paths that are not ideal in a number of situations
- Restrict Project Naming - inconvenient because it removes the ability to have spaces in job names, making them harder to read, especially if you have a large number of them
- Custom Workspaces - inconvenient because the requirement for uniqueness is on the user, and it requires much more thought when creating projects if you have a high number of them

The Whitespace Replacement plug-in still allows the Job name to be the unique identifer for the project, keeps them easy to read and requires no additional work to create job paths that will work with scripts or processes that cannot handle spaces in path names. In the case of a multi branch pipeline, it could happen that a branch contains a forward slash in it's name, especially when using GitFlow. This character will be encoded by Jenkins and could potentially break some tools handled by different plugins, for example Maven. To overcome this limitation, `%2F` will also be replaced by an underscore.


### Current Build Status
Master:  
[![Build Status](https://travis-ci.org/jenkinsci/ws-ws-replacement-plugin.svg?branch=master)](https://travis-ci.org/jenkinsci/ws-ws-replacement-plugin)

Develop:  
[![Build Status](https://travis-ci.org/jenkinsci/ws-ws-replacement-plugin.svg?branch=develop)](https://travis-ci.org/jenkinsci/ws-ws-replacement-plugin)

