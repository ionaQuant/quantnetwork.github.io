---
title: Hello World
parent: Tutorials
layout: page
nav_order: 1
---

# Hello World
Follow these steps to run a simple PayScript to see it print a message to the logs.

```groovy
trigger = "on_demand";
logMessage("Hello world!");
```

The first line defines an on-demand trigger, which is a type of trigger that can be run manually by pressing a button. The trigger definition can be considered anologous to the entry point in other languages.

The second line calls the [built-in function][built-in function] `logMessage` to display the message on the logs.

## 1. Download the script
You can download the hello world script from the [example scripts][hello world] page. Alternatively, you can copy the code and save it yourself. Make sure it has the `.groovy` extension.

## 2. Deploy the script
<div style="text-align: center;">
    <img src="/assets/images/tutorial0/hello-world-import.png" width="90%">
</div>

In the *Template Library* tab, import the script through the import button. You will be prompted for a name, description, and the file itself. 

Once the script has been imported, it needs to be deployed. You can do this by clicking the green rocket button under the "ACTIONS" column. You should see the script's value in the "Status" column change from "Undeployed" to "Deployed".
<div style="text-align: center;">
    <img src="/assets/images/tutorial0/hello-world-deploy.png" width="90%">
</div>

# Next:
- [Automations]

[Automations]: /docs/tutorials/automations
[hello world]: /docs/payscript/example_scripts/hello_world
[built-in function]: /docs/payscript/built_in_functions
