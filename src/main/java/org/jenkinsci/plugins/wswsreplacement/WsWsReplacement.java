// Package Name
package org.jenkinsci.plugins.wswsreplacement;

// Imports
import hudson.Extension;

import hudson.FilePath;
import hudson.model.TopLevelItem;
import hudson.model.Node;
import hudson.model.Slave;

import jenkins.slaves.WorkspaceLocator;

import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;

//
// Workspace extension
//
@Extension
@Restricted(NoExternalUse.class)
public class WsWsReplacement extends WorkspaceLocator
{
    //
    // Returns the path of the workspace substituting underscores for spaces
    //
    @Override
    public FilePath locate(TopLevelItem item, Node node)
    {
        // This only works on slaves for the time being
        Slave slave = getSlave(node);
        if (slave == null)
            return null;

        // Get the path to the job
        FilePath workspacePath = getWorkSpacePath(slave, item);
        if (workspacePath == null)
            return null;

        // Return the path
        return workspacePath;
    }

    //
    // Returns the slave node we're working on
    //
    private Slave getSlave(Node node)
    {
        // It needs to be a slave
        if (!(node instanceof Slave))
            return null;

        // Get the slave and send it back
        return (Slave) node;
    }

    //
    // Gets the path of the job in the workspace
    //
    private FilePath getWorkSpacePath(Slave slave, TopLevelItem item)
    {
        // Replace the spaces in the job name with underscores
        String jobName = item.getFullName().replaceAll("\\s","_").replace("%2F","_");

        // Get the full path for this job on the slave
        FilePath pathToUse = buildCompletePath(jobName, slave);
        if (pathToUse == null)
            return null;

        // Return the final path
        return pathToUse;
    }

    //
    // Returns the full path of the workspace
    //
    private FilePath buildCompletePath(String jobName, Slave slave)
    {
        // Get the root of the work space
        FilePath wsRoot = slave.getWorkspaceRoot();
        if (wsRoot == null)
            return null;

        // Return the full path
        return wsRoot.child(jobName);
    }
}
