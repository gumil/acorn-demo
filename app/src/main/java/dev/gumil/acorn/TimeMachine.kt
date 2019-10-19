package dev.gumil.acorn

import com.nhaarman.acorn.navigation.Navigator
import com.nhaarman.acorn.navigation.TransitionData
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene

class TimeMachine(
    initialNode: NavigatorNode
) : Navigator.Events {

    private val historyStack = mutableListOf<NavigatorNode>().apply {
        add(initialNode)
    }

    override fun finished() {
        //do nothing
    }

    override fun scene(scene: Scene<out Container>, data: TransitionData?) {
        historyStack.last().add(SceneNode(scene::javaClass.name, data))
    }

    fun addNode(node: NavigatorNode) {
        historyStack.add(node)
    }

    fun nodeVisited() {
        historyStack.add(NavigatorNode(historyStack[historyStack.lastIndex - 1].name))
    }
}

interface TimeNode {
    val name: String
}

class NavigatorNode(
    override val name: String
): TimeNode {

    private val historyStack = mutableListOf<SceneNode>()

    fun add(element: SceneNode) {
        historyStack.add(element)
    }
}

class SceneNode(
    override val name: String,
    val transitionData: TransitionData?
): TimeNode
