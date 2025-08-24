import { TodoPriority } from '@/api/models/TodoPriority'
import { TodoStatus } from '@/api/models/TodoStatus'

export interface TodoDto {
  id: string

  title: string

  description: string

  status: TodoStatus

  priority: TodoPriority
}
